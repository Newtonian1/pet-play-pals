package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.util.List;

public interface PetDao {

    //list all pets
    public List<Pet> getAllPets();

    //find pet by petID
    public Pet getPetByPetId(int petId);

    //register a pet
    public Pet registerPet(Pet newPet);

    //update pet
    public void updatePet(Pet petToUpdate, int id);

    //delete pet
    public void deletePet(int petId);


}
