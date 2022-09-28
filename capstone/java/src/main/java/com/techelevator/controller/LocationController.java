package com.techelevator.controller;

import com.techelevator.dao.LocationDao;
import com.techelevator.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class LocationController {

    private LocationDao locationDao;

    public LocationController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @RequestMapping(path = "/locations", method = RequestMethod.GET)
    public List<Location> listLocations() {
        return locationDao.getLocations();
    }

    @RequestMapping(path = "/locations/{id}", method = RequestMethod.GET)
    public Location getLocationById(@PathVariable int id) {
        return locationDao.getLocationById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/locations/{id}", method = RequestMethod.PUT)
    public boolean updateLocationById(@RequestBody Location updatedLocation, @PathVariable int id) {
        return locationDao.updateLocation(updatedLocation, id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/locations/{id}", method = RequestMethod.DELETE)
    public boolean deleteLocation(@PathVariable int id) {
        return locationDao.deleteLocation(id);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/locations", method = RequestMethod.POST)
    public Location createLocation(@RequestBody Location newLocation) {
        return locationDao.createLocation(newLocation);
    }
}
