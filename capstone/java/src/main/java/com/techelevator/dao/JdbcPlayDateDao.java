package com.techelevator.dao;

import com.techelevator.model.PlayDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlayDateDao implements PlayDateDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayDateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PlayDate> getAllPlayDates() {
        List<PlayDate> allPlayDates = new ArrayList<>();
        String sql = "SELECT pd.play_date_id, pd.location_id, pd.play_date_time, p.pet_id AS first_pet_id,p2.pet_id AS second_pet_id,s.status\n" +
                "FROM play_date pd\n" +
                "JOIN pets p ON pd.first_pet_id = p.pet_id\n" +
                "JOIN pets p2 ON pd.second_pet_id = p2.pet_id\n" +
                "JOIN statuses s ON pd.status_id = s.status_id\n;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            PlayDate playDate = mapRowToPlayDate(results);
            allPlayDates.add(playDate);
        }
        return allPlayDates; //working in pm
    }

    @Override
    public PlayDate getPlayDateById(int playDateId) {
        String sql = "SELECT pd.play_date_id, pd.location_id, pd.play_date_time, p.pet_id AS first_pet_id,p2.pet_id AS second_pet_id,s.status\n" +
                "FROM play_date pd\n" +
                "JOIN pets p ON pd.first_pet_id = p.pet_id\n" +
                "JOIN pets p2 ON pd.second_pet_id = p2.pet_id\n" +
                "JOIN statuses s ON pd.status_id = s.status_id\n" +
                "WHERE pd.play_date_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,playDateId);
        if (results.next()){
            return mapRowToPlayDate(results);
        }else return null;
//working in pm

    }

    @Override
    public List<PlayDate> getPlayDateByLocalDateAndTime(LocalDateTime playDateLocalDateAndTime) {
        List<PlayDate> playDatesByLocalDateAndTime = new ArrayList<>();

        String sql = "SELECT pd.play_date_id, pd.location_id, pd.play_date_time, p.pet_id AS first_pet_id,p2.pet_id AS second_pet_id,s.status\n" +
                "FROM play_date pd\n" +
                "JOIN pets p ON pd.first_pet_id = p.pet_id\n" +
                "JOIN pets p2 ON pd.second_pet_id = p2.pet_id\n" +
                "JOIN statuses s ON pd.status_id = s.status_id\n" +
                "WHERE pd.play_date_time = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playDateLocalDateAndTime);
while(results.next()){
    PlayDate playDate = mapRowToPlayDate(results);
    playDatesByLocalDateAndTime.add(playDate);
}
        return playDatesByLocalDateAndTime;
    }



    @Override
    public PlayDate createPlayDate(PlayDate playDate) {
        String sql = "INSERT INTO play_date (play_date_time,location_id,first_pet_id,second_pet_id,status_id) VALUES (?, ?, ?,?,(SELECT status_id FROM statuses WHERE status=?)) RETURNING play_date_id;";
Integer newPlayDateId = jdbcTemplate.queryForObject(
        sql,
        Integer.class,
        playDate.getPlayDateTimeStamp(),
        playDate.getLocationId(),
        playDate.getPetOneId(),
        playDate.getPetTwoId(),
        playDate.getStatus()
);
if (newPlayDateId!=null){
    return getPlayDateById(newPlayDateId);
} else
        return null;
    }

    @Override
    public boolean updatePlayDate(PlayDate playDateToUpdate,int id) {
String sql = "UPDATE play_date\n" +
        "SET play_date_time = ?,\n" +
        "location_id =?,\n" +
        "first_pet_id=?,\n" +
        "second_pet_id=?,\n" +
        "status=(SELECT status_id FROM statuses WHERE status =?)\n" +
        "WHERE play_date_id=?;";

int success = jdbcTemplate.update(
        sql,
        playDateToUpdate.getLocationId(),
        Timestamp.valueOf(playDateToUpdate.getPlayDateTimeStamp()),
        playDateToUpdate.getPetTwoId(),
        playDateToUpdate.getPetTwoId(),
        playDateToUpdate.getStatus(),
        id);

return success ==1;
    }

    @Override
    public boolean deletePlayDate(int playDateId) {
        String sql = "DELETE FROM play_date WHERE play_date_id =?";
       int success =  jdbcTemplate.update(sql,playDateId);
        return success == 1;
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
