package com.techelevator.controller;

import com.techelevator.dao.PlayDateDao;
import com.techelevator.model.PlayDate;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PlayDateController {
    private PlayDateDao playDateDao;

    public PlayDateController(PlayDateDao playDateDao) {
        this.playDateDao = playDateDao;
    }

    @RequestMapping(path = "/playdates", method = RequestMethod.GET)
    public List<PlayDate> listPlayDates() {
        return playDateDao.getAllPlayDates();
    }

    @RequestMapping(path = "playdates/{id}", method = RequestMethod.GET)
    public PlayDate getPlayDateById(@PathVariable int id) {
        return playDateDao.getPlayDateById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/playdates", method = RequestMethod.POST)
    public PlayDate createPlayDate(@RequestBody PlayDate newPlayDate) {
        return playDateDao.createPlayDate(newPlayDate);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/playdates/{id}", method = RequestMethod.PUT)
    public void updatePlayDateById(@RequestBody PlayDate updatedPlayDate, @PathVariable int id) {
        playDateDao.updatePlayDate(updatedPlayDate, id);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/playdates/{id}", method = RequestMethod.DELETE)
    public void deletePlayDate(@PathVariable int id) {
        playDateDao.deletePlayDate(id);
    }
}