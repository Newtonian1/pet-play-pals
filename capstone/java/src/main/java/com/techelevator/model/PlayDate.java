package com.techelevator.model;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PlayDate {
    private int playDateId;
    private int hostPetId;
    private List<Integer> attendingPetIds;
    private String status;
    private int locationId;
    private LocalDateTime playDateTimeStamp;

    public PlayDate(){}

    public PlayDate(int playDateId, int hostPetId, List<Integer> attendingPetIds, String status, int locationId, LocalDateTime playDateTimeStamp) {
        this.playDateId = playDateId;
        this.hostPetId = hostPetId;
        this.attendingPetIds = attendingPetIds;
        this.status = status;
        this.locationId = locationId;
        this.playDateTimeStamp = playDateTimeStamp;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayDate playDate = (PlayDate) o;
        return playDateId == playDate.playDateId &&
                hostPetId == playDate.hostPetId &&
                locationId == playDate.locationId &&
                Objects.equals(attendingPetIds, playDate.attendingPetIds) &&
                Objects.equals(status, playDate.status) &&
                Objects.equals(playDateTimeStamp, playDate.playDateTimeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playDateId, hostPetId, attendingPetIds, status, locationId, playDateTimeStamp);
    }
}

