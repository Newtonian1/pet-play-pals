package com.techelevator.dao;

import com.techelevator.model.PlayDate;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface PlayDateDao {
    //list all pd
    public List<PlayDate> getAllPlayDates();

    //show pd by pd id
    public PlayDate getPlayDateById(int playDateId);

    //list of pd by date
    public List<PlayDate> getPlayDatesByDate(Date dateOfPlayDate);

    //list of pd by time
    public List<PlayDate> getPlayDatesByTime(Time timeOfPlayDate);

    //create pd
    public PlayDate createPlayDate(PlayDate playDate);

    //update pd
    public PlayDate updatePlayDate(PlayDate playDateToUpdate);

    //delete pd
    public void deletePlayDate(int playDateId);

    //join a pd
    public void joinPlayDate(PlayDate playDateToJoin);

    //decline a pd
    public void declinePlayDate(PlayDate invitedPlayDate); //should we use PD ID?

}
