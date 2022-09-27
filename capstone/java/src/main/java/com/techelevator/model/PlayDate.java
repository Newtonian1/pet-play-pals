package com.techelevator.model;

import java.sql.Date;
import java.sql.Time;

public class PlayDate {
    private int playDateId;
    private Time playDateTime;
    private Date DateOfPlayDate;
    private Pet petOne;  //pet host?
    private Pet petTwo;  //pet guest?
    private int statusId;
    //location

    public int getPlayDateId() {
        return playDateId;
    }

    public void setPlayDateId(int playDateId) {
        this.playDateId = playDateId;
    }

    public Time getPlayDateTime() {
        return playDateTime;
    }

    public void setPlayDateTime(Time playDateTime) {
        this.playDateTime = playDateTime;
    }

    public Date getDateOfPlayDate() {
        return DateOfPlayDate;
    }

    public void setDateOfPlayDate(Date dateOfPlayDate) {
        DateOfPlayDate = dateOfPlayDate;
    }

    public Pet getPetOne() {
        return petOne;
    }

    public void setPetOne(Pet petOne) {
        this.petOne = petOne;
    }

    public Pet getPetTwo() {
        return petTwo;
    }

    public void setPetTwo(Pet petTwo) {
        this.petTwo = petTwo;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
