package com.techelevator.dao;

import com.techelevator.model.PetPicture;

import java.util.List;

public interface PetPictureDao {

    List<PetPicture> getPetPictures();

    PetPicture getPetPictureByPetId(int id);

    boolean updatePetPictureByPetId(PetPicture petPicture, int id);

    PetPicture addPetPicture(PetPicture petPicture);

    boolean deletePetPictureByPetId(int id);

}
