package com.techelevator.dao;

import com.techelevator.model.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcLocationDaoTest extends BaseDaoTests {
    protected static final Location LOCATION_1 = new Location(4001, "Garfield's Place", "135 Garfield Place", "", "Cincinnati", "OH", "45202", 39.103830, -84.516410);
    protected static final Location LOCATION_2 = new Location(4002, "Bark Park", "1 W Corry St", "", "Cincinnati", "OH", "45219", 39.128870, -84.509940);
    protected static final Location LOCATION_3 = new Location(4003, "Bob's Boxes for Cats to Sit In", "62 S Washington St", "", "Tiffin", "OH", "44883", 41.115772, -83.176672);

    private JdbcLocationDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcLocationDao(jdbcTemplate);
    }

    @Test
    public void get_locations_returns_all_locations() {
        List<Location> locations = sut.getLocations();
        Assert.assertNotNull(locations);
        Assert.assertEquals(3, locations.size());
        Assert.assertEquals(LOCATION_1, locations.get(0));
        Assert.assertEquals(LOCATION_2, locations.get(1));
        Assert.assertEquals(LOCATION_3, locations.get(2));
    }

    @Test
    public void get_location_by_id_returns_expected_location() {
        Location location = sut.getLocationById(4001);
        Assert.assertEquals(LOCATION_1, location);
        location = sut.getLocationById(4002);
        Assert.assertEquals(LOCATION_2, location);
        location = sut.getLocationById(4003);
        Assert.assertEquals(LOCATION_3, location);
    }

    @Test
    public void create_location_creates_and_returns_a_new_location() {
        Location newLocation = new Location(-1, "Cat Park", "200 Crossroads Blvd", "", "Bucyrus", "OH", "44820", 40.832327, -82.961928);
        Location createdLocation = sut.createLocation(newLocation);
        newLocation.setLocationId(createdLocation.getLocationId());
        Assert.assertEquals(newLocation, createdLocation);
    }
}