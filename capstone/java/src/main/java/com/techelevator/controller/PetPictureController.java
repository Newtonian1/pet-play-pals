package com.techelevator.controller;

import com.techelevator.dao.PetPictureDao;
import com.techelevator.model.PetPicture;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PetPictureController {
    private PetPictureDao petPictureDao;

    public PetPictureController(PetPictureDao petPictureDao) {
        this.petPictureDao = petPictureDao;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/images/upload", method = RequestMethod.POST)
    public Integer addPetPicture(@RequestBody PetPicture petPicture) {
        return petPictureDao.addPetPicture(petPicture);
    }
}
