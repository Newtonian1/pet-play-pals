package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.util.List;

public interface PetDao {

    //list all pets
    List<Pet> getAllPets();

    //find pet by petID
    public Pet getPetByPetId(int petId);

    //find pet by type - find all cats / dogs / etc
    List<Pet> getPetsByType(String petType);

    //find pets by user id - list
    List<Pet>

    //find pets by personality - list

    //pets by breed - list

    //register a pet
    public Pet registerPet(Pet newPet);

    //update pet
    public void updatePet(Pet petToUpdate);

    //delete pet
    public void deletePet(Pet petToDelete);


}
