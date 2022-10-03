package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcPetDaoTest extends BaseDaoTests {
    static List<String> personalities = new ArrayList<>();
    protected static final Pet PET_1 = new Pet(2001, "Minnie", 1003, "dog", "Who knows", "female", "medium", personalities, true, true, "Chaos incarnate");

    private JdbcPetDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcPetDao(jdbcTemplate);
    }
}