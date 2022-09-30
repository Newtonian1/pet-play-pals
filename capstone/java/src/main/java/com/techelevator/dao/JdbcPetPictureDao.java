package com.techelevator.dao;

import com.techelevator.model.PetPicture;

import java.util.List;

public class JdbcPetPictureDao implements PetPictureDao {

    @Override
    public List<PetPicture> getPetPictures() {
        return null;
    }

    @Override
    public PetPicture getPetPictureByPetId(int id) {
        return null;
    }

    @Override
    public boolean updatePetPictureByPetId(PetPicture petPicture, int id) {
        return false;
    }

    @Override
    public PetPicture addPetPicture(PetPicture petPicture) {
        return null;
    }

    @Override
    public boolean deletePetPictureByPetId(int id) {
        return false;
    }
}
