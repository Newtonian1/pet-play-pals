package com.techelevator.model;


import java.time.LocalDateTime;
import java.util.List;

public class PlayDate {
    private int playDateId;
    private int hostPetId;
    private List<Integer> attendingPetIds;
    private String status;
    private int locationId;
    private LocalDateTime playDateTimeStamp;

    public int getPlayDateId() {
        return playDateId;
    }

    public void setPlayDateId(int playDateId) {
        this.playDateId = playDateId;
    }

    public int getHostPetId() {
        return hostPetId;
    }

    public void setHostPetId(int hostPetId) {
        this.hostPetId = hostPetId;
    }

    public List<Integer> getAttendingPetIds() {
        return attendingPetIds;
    }

    public void setAttendingPetIds(List<Integer> attendingPetIds) {
        this.attendingPetIds = attendingPetIds;
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
