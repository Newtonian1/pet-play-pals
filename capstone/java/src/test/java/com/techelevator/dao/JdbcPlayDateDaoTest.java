package com.techelevator.dao;

import com.techelevator.model.PlayDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcPlayDateDaoTest extends BaseDaoTests{

    private static List<Integer> testPetIds = new ArrayList<>();

    protected static final PlayDate PLAY_DATE_1 = new PlayDate(9001, 2002, testPetIds,"5001",2002, LocalDateTime.now());
    protected static final PlayDate PLAY_DATE_2 = new PlayDate(9002, 2003, testPetIds,"5002",2002,LocalDateTime.now());
    protected static final PlayDate PLAY_DATE_3 = new PlayDate(9003,2002,testPetIds,"5003",2001,LocalDateTime.now());

private JdbcPlayDateDao sut;

@Before
    public void setup(){
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    sut = new JdbcPlayDateDao(jdbcTemplate);
}

@Test
    public void get_all_play_dates_returns_all_play_dates(){
    testPetIds.add(0,2001);
    List<PlayDate> playDates = sut.getAllPlayDates();
    Assert.assertNotNull(playDates);
    Assert.assertEquals(3,playDates.size());
    Assert.assertEquals(PLAY_DATE_1,playDates.get(0));
    Assert.assertEquals(PLAY_DATE_2,playDates.get(1));
    Assert.assertEquals(PLAY_DATE_3, playDates.get(2));


}

@Test
    public void get_play_date_by_play_date_id_returns_expected_play_date(){


}

}
