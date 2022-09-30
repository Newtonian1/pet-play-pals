package com.techelevator.dao;

import com.techelevator.model.PlayDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlayDateDao implements PlayDateDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayDateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PlayDate> getAllPlayDates() {
        List<PlayDate> allPlayDates = new ArrayList<>();
        String sql = "SELECT play_date_id, play_date_time, location_id, host_pet_id, s.status\n" +
                "FROM play_date pd\n" +
                "JOIN statuses s ON pd.status_id = s.status_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            PlayDate playDate = mapRowToPlayDate(results);
            allPlayDates.add(playDate);
        }
        return allPlayDates; //working in pm
    }

    @Override
    public PlayDate getPlayDateById(int playDateId) {
        String sql = "SELECT play_date_id, play_date_time, location_id, host_pet_id, s.status\n" +
                "FROM play_date pd\n" +
                "JOIN statuses s ON pd.status_id = s.status_id\n" +
                "WHERE pd.play_date_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playDateId);
        if (results.next()) {
            return mapRowToPlayDate(results);
        } else return null; //working in pm
    }

    @Override
    public PlayDate createPlayDate(PlayDate playDate) {
        String sql = "INSERT INTO play_date (play_date_time, location_id, host_pet_id, status_id) \n" +
                "VALUES (?, ?, ?, (SELECT status_id FROM statuses WHERE status = ?)) \n" +
                "RETURNING play_date_id;";
        Integer newPlayDateId = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                playDate.getPlayDateTimeStamp(),
                playDate.getLocationId(),
                playDate.getHostPetId(),
                playDate.getStatus()
        );
        if (newPlayDateId != null) {
            addPetsToPlayDateById(playDate, newPlayDateId);
            return getPlayDateById(newPlayDateId);
        } else
            return null;
    }

    @Override
    public void updatePlayDate(PlayDate playDateToUpdate, int id) {
        String sql = "UPDATE play_date\n" +
                "SET play_date_time = ?,\n" +
                "location_id = ?,\n" +
                "host_pet_id = ?,\n" +
                "status_id = (SELECT status_id FROM statuses WHERE status = ?)\n" +
                "WHERE play_date_id = ?;";

        jdbcTemplate.update(
                sql,
                playDateToUpdate.getPlayDateTimeStamp(),
                playDateToUpdate.getLocationId(),
                playDateToUpdate.getHostPetId(),
                playDateToUpdate.getStatus(),
                id
        );
        deletePetsFromPlayDatesById(id);
        addPetsToPlayDateById(playDateToUpdate, id);
    }

    @Override
    public void deletePlayDate(int playDateId) {
        deletePetsFromPlayDatesById(playDateId);
        String sql = "DELETE FROM play_date WHERE play_date_id =?";
        jdbcTemplate.update(sql, playDateId);
    }

    private List<Integer> getPetsAttendingPlayDateById(int playDateId) {
        List<Integer> petIds = new ArrayList<>();
        String sql = "SELECT pet_id\n" +
                "FROM play_date_pet\n" +
                "WHERE play_date_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playDateId);
        while (results.next()) {
            petIds.add(results.getInt("pet_id"));
        }
        return petIds;
    }

    private void deletePetsFromPlayDatesById(int playDateId) {
        String sql = "DELETE FROM play_date_pet\n" +
                "WHERE play_date_id = ?;";
        jdbcTemplate.update(sql, playDateId);
    }

    private void addPetsToPlayDateById(PlayDate playDate, Integer playDateId) {
        if (playDate.getAttendingPetIds() != null) {
            for (Integer petId : playDate.getAttendingPetIds()) {
                String sql = "INSERT INTO play_date_pet (play_date_id, pet_id) \n" +
                        "VALUES (?, ?)\n" +
                        "ON CONFLICT DO NOTHING RETURNING play_date_id;";
                jdbcTemplate.queryForObject(sql, Integer.class, playDateId, petId);
            }
        }
    }

    private PlayDate mapRowToPlayDate(SqlRowSet sql) {
        PlayDate playDate = new PlayDate();
        playDate.setPlayDateId(sql.getInt("play_date_id"));
        playDate.setLocationId(sql.getInt("location_id"));
        playDate.setHostPetId(sql.getInt("host_pet_id"));
        playDate.setStatus(sql.getString("status"));
        if (sql.getTimestamp("play_date_time") != null) {
            playDate.setPlayDateTimeStamp(sql.getTimestamp("play_date_time").toLocalDateTime());
        }
        playDate.setAttendingPetIds(getPetsAttendingPlayDateById(playDate.getPlayDateId()));
        return playDate;
    }
}
