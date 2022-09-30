package com.techelevator.dao;

import com.techelevator.model.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLocationDao implements LocationDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLocationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Location> getLocations() {
        List<Location> allLocations = new ArrayList<>();
        String sql = "SELECT location_name, location_id, address_1, address_2, city, state_abbreviation, zip_code, latitude, longitude \n" +
                "FROM locations;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Location location = mapRowToLocation(results);
            allLocations.add(location);
        }
        return allLocations;
    }

    @Override
    public Location getLocationById(int id) {
        Location location = new Location();
        String sql = "SELECT location_name, location_id, address_1, address_2, city, state_abbreviation, zip_code, latitude, longitude \n" +
                "FROM locations\n" +
                "WHERE location_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            return mapRowToLocation(result);
        } else {
            return null;
        }
    }

    @Override
    public boolean updateLocation(Location updatedLocation, int id) {
        String sql = "UPDATE locations\n" +
                "SET location_name = ?," +
                "\taddress_1 = ?,\n" +
                "\taddress_2 = ?,\n" +
                "\tcity = ?,\n" +
                "\tstate_abbreviation = ?,\n" +
                "\tzip_code = ?,\n" +
                "\tlatitude = ?,\n" +
                "\tlongitude = ?\n" +
                "WHERE location_id = ?;";
        int success = jdbcTemplate.update(
                sql,
                updatedLocation.getAddress1(),
                updatedLocation.getAddress2(),
                updatedLocation.getCity(),
                updatedLocation.getStateAbbreviation(),
                updatedLocation.getZipCode(),
                updatedLocation.getLatitude(),
                updatedLocation.getLongitude(),
                id);
        return success == 1;
    }

    @Override
    public Location createLocation(Location newLocation) {
        String sql = "INSERT INTO locations (location_name, address_1,address_2,city,state_abbreviation,zip_code,latitude,longitude) \n" +
                "VALUES (?,?,?,?,?,?,?,?)\n" +
                "RETURNING location_id;";
        Integer newLocationId = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                newLocation.getLocationName(),
                newLocation.getAddress1(),
                newLocation.getAddress2(),
                newLocation.getCity(),
                newLocation.getStateAbbreviation(),
                newLocation.getZipCode(),
                newLocation.getLatitude(),
                newLocation.getLongitude()
        );
        if (newLocationId != null) {
            return getLocationById(newLocationId);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteLocation(int id) {
        String sql = "DELETE FROM locations\n" +
                "WHERE location_id = ?;";
        int success = jdbcTemplate.update(sql, id);
        return success == 1;
    }

    private Location mapRowToLocation(SqlRowSet results) {
        Location location = new Location();
        location.setLocationId(results.getInt("location_id"));
        location.setLocationName(results.getString("location_name"));
        location.setAddress1(results.getString("address_1"));
        location.setAddress2(results.getString("address_2"));
        location.setCity(results.getString("city"));
        location.setStateAbbreviation(results.getString("state_abbreviation"));
        location.setZipCode(results.getString("zip_code"));
        location.setLatitude(results.getDouble("latitude"));
        location.setLongitude(results.getDouble("longitude"));
        return location;
    }
}
