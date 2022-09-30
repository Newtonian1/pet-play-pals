package com.techelevator.dao;

import com.techelevator.model.PlayDate;

import java.util.List;

public interface PlayDateDao {
    //list all pd
    public List<PlayDate> getAllPlayDates();

    //show pd by pd id
    public PlayDate getPlayDateById(int playDateId);

    //create pd
    public PlayDate createPlayDate(PlayDate playDate);

    //update pd
    public void updatePlayDate(PlayDate playDateToUpdate, int playDateId);

    //delete pd
    public void deletePlayDate(int playDateId);


}
