package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcPetDaoTest extends BaseDaoTests {
    static List<String> personalities = new ArrayList<>();

    private JdbcPetDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcPetDao(jdbcTemplate);
    }
}