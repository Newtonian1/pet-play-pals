package com.techelevator.dao;

import com.techelevator.model.PlayDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

public class JdbcPlayDateDao implements PlayDateDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayDateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PlayDate> getAllPlayDates() {
        List<PlayDate> allPlayDates = new ArrayList<>();
        String sql = "SELECT * FROM play_dates";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            PlayDate playDate;
        }
        return null;
    }

    @Override
    public PlayDate getPlayDateById(int playDateId) {
        String sql = "SELECT pd.play_date_id, pd.location_id, pd.play_date_time, p.pet_id,p2.pet_id,s.status\n" +
                "FROM play_date pd\n" +
                "JOIN pets p ON pd.first_pet_id = p.pet_id\n" +
                "JOIN pets p2 ON pd.second_pet_id = p2.pet_id\n" +
                "JOIN statuses s ON pd.status_id = s.status_id\n" +
                "WHERE pd.play_date_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,playDateId);
        

        return null;
    }

    @Override
    public LocalDateTime getPlayDateByLocalDateAndTime(LocalDateTime playDateLocalDateAndTime) {
        List<PlayDate> playDatesByLocalDateAndTime = new ArrayList<>();

        String sql = "SELECT pd.play_date_id, pd.location_id, pd.play_date_time, p.pet_id,p2.pet_id,s.status\n" +
                "FROM play_date pd\n" +
                "JOIN pets p ON pd.first_pet_id = p.pet_id\n" +
                "JOIN pets p2 ON pd.second_pet_id = p2.pet_id\n" +
                "JOIN statuses s ON pd.status_id = s.status_id\n" +
                "WHERE pd.play_date_time = ?;";

        return null;
    }



    @Override
    public PlayDate createPlayDate(PlayDate playDate) {
        String sql = "INSERT INTO play_dates \n"+
                "\t(play_date_time, \n"+
                "\t location_id, \n"+
                "\t first_pet_id \n"+
                "\t second_pet_id \n"+
                "\t status_id) \n"+
                "\t VALUES \n"+
                "\t(?";

        return null;
    }

    @Override
    public PlayDate updatePlayDate(PlayDate playDateToUpdate) {
        return null;
    }

    @Override
    public void deletePlayDate(int playDateId) {
        String sql = "DELETE FROM play_dates WHERE play_date_id =?";
        jdbcTemplate.update(sql,playDateId);

    }

    @Override
    public void joinPlayDate(PlayDate playDateToJoin) {

    }

    @Override
    public void declinePlayDate(PlayDate invitedPlayDate) {

    }

    private PlayDate mapRowToPlayDate(SqlRowSet sql){

        PlayDate playDate = new PlayDate();
        playDate.setPlayDateId(sql.getInt("play_date_id"));
        playDate.setLocationId(sql.getInt("location_id"));
        playDate.setPetOneId(sql.getInt("first_pet_id"));
        playDate.setPetTwoId(sql.getInt("second_pet_id"));
        playDate.setStatus(sql.getString("status"));
        playDate.setPlayDateTimeStamp(sql.getTimestamp("play_date_time").toLocalDateTime());


        return playDate;
    }

}
