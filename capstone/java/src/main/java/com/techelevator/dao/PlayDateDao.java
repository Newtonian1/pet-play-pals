package com.techelevator.dao;

import com.techelevator.model.PlayDate;


import java.time.LocalDateTime;

import java.util.List;

public interface PlayDateDao {
    //list all pd
    public List<PlayDate> getAllPlayDates();

    //show pd by pd id
    public PlayDate getPlayDateById(int playDateId);

    //local date
    public List<PlayDate> getPlayDateByLocalDateAndTime(LocalDateTime playDateLocalDateAndTime);

    //create pd
    public PlayDate createPlayDate(PlayDate playDate);

    //update pd
    public boolean updatePlayDate(PlayDate playDateToUpdate,int playDateId);

    //delete pd
    public boolean deletePlayDate(int playDateId);



}
