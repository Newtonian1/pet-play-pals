package com.techelevator.dao;

import com.techelevator.model.Location;

import java.util.List;

public interface LocationDao {

    List<Location> getLocations();

    Location getLocationById(int id);

    boolean updateLocation(Location updatedLocation, int id);

    Location createLocation(Location location);

    boolean deleteLocation(int id);
}
