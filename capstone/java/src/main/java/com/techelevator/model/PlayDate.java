package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PlayDate {
    private int playDateId;
    @JsonProperty("first_pet_id")
    private int petOneId;
    @JsonProperty("second_pet_id")
    private int petTwoId;
    private String status;
    private int locationId;
    private LocalDateTime playDateTimeStamp;

    public int getPlayDateId() {
        return playDateId;
    }

    public void setPlayDateId(int playDateId) {
        this.playDateId = playDateId;
    }

    public int getPetOneId() {
        return petOneId;
    }

    public void setPetOneId(int petOneId) {
        this.petOneId = petOneId;
    }

    public int getPetTwoId() {
        return petTwoId;
    }

    public void setPetTwoId(int petTwoId) {
        this.petTwoId = petTwoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public LocalDateTime getPlayDateTimeStamp() {
        return playDateTimeStamp;
    }

    public void setPlayDateTimeStamp(LocalDateTime playDateTimeStamp) {
        this.playDateTimeStamp = playDateTimeStamp;
    }
}
