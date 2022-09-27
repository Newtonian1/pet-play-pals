package com.techelevator.dao;

import com.techelevator.model.PlayDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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
            PlayDate playDate
        }
        return null;
    }

    @Override
    public PlayDate getPlayDateById(int playDateId) {
        return null;
    }

    @Override
    public List<PlayDate> getPlayDatesByDate(Date dateOfPlayDate) {
        return null;
    }

    @Override
    public List<PlayDate> getPlayDatesByTime(Time timeOfPlayDate) {
        return null;
    }

    @Override
    public PlayDate createPlayDate(PlayDate playDate) {
        return null;
    }

    @Override
    public PlayDate updatePlayDate(PlayDate playDateToUpdate) {
        return null;
    }

    @Override
    public void deletePlayDate(int playDateId) {

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

        return playDate
    }

}
