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

    protected static final PlayDate PLAY_DATE_1 = new PlayDate(9001, 2001, testPetIds,"approved",4001,LocalDateTime.parse("2022-10-31T09:30:20"));
    protected static final PlayDate PLAY_DATE_2 = new PlayDate(9002, 2001, testPetIds,"rejected",4002,LocalDateTime.parse("2022-10-31T09:30:20"));
    protected static final PlayDate PLAY_DATE_3 = new PlayDate(9003,2002,testPetIds,"pending",4003,LocalDateTime.parse("2022-10-31T09:30:20"));

private JdbcPlayDateDao sut;

@Before
    public void setup(){
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    sut = new JdbcPlayDateDao(jdbcTemplate);
}

@Test
    public void get_all_play_dates_returns_all_play_dates(){
    testPetIds.add(0,2003);
    List<PlayDate> playDates = sut.getAllPlayDates();
    Assert.assertNotNull(playDates);
    Assert.assertEquals(3,playDates.size());
//    this.assertPlayDates(PLAY_DATE_1,playDates.get(0));
    testPetIds.remove(0);
    testPetIds.add(0,2002);
    this.assertPlayDates(PLAY_DATE_2,playDates.get(1));
    this.assertPlayDates(PLAY_DATE_3,playDates.get(2));



}

@Test
    public void get_play_date_by_play_date_id_returns_expected_play_date(){
    testPetIds.add(0,2003);
PlayDate playDate = sut.getPlayDateById(9001);
this.assertPlayDates(PLAY_DATE_1,playDate);

}

@Test
public void create_play_date_creates_and_returns_play_date(){
    testPetIds.add(0,2002);
    PlayDate playDate = new PlayDate(9004,2001,testPetIds,"approved",4001,LocalDateTime.parse("2022-10-31T09:30:20"));
    PlayDate createdPlayDate = sut.createPlayDate(playDate);
    this.assertPlayDates(playDate,createdPlayDate);

}

//update
    @Test
    public void update_play_date_has_expected_values(){
    PlayDate updatedPlayDate = sut.getPlayDateById(9003);
    updatedPlayDate.setHostPetId(2001);
    updatedPlayDate.setLocationId(4001);

    sut.updatePlayDate(updatedPlayDate,9003);

    PlayDate test = sut.getPlayDateById(9003);
    this.assertPlayDates(updatedPlayDate,test);


    }

private void assertPlayDates(PlayDate playDate,PlayDate otherPlayDate){
    Assert.assertEquals(playDate.getPlayDateId(), otherPlayDate.getPlayDateId());
    Assert.assertEquals(playDate.getLocationId(), otherPlayDate.getLocationId());
    Assert.assertEquals(playDate.getAttendingPetIds(), otherPlayDate.getAttendingPetIds());
    Assert.assertEquals(playDate.getStatus(), otherPlayDate.getStatus());
    Assert.assertEquals(playDate.getHostPetId(), otherPlayDate.getHostPetId());
    Assert.assertEquals(playDate.getPlayDateTimeStamp(), otherPlayDate.getPlayDateTimeStamp());

}






}
