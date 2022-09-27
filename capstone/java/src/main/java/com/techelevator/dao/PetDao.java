package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.util.List;

public interface PetDao {

    //list all pets
    public List<Pet> getAllPets();

    //find pet by petID
    public Pet getPetByPetId(int petId);

    //find pet by type - find all cats / dogs / etc
    public List<Pet> getPetsByType(String petType);

    //find pets by owner id - list
    public List<Pet> getPetsByOwnerId(int ownerId);

    //find pets by personality - list
    public List<Pet> getPetsByPersonalityType(String personality);

    //pets by breed - list
    public List<Pet> getPetsByBreed(String breed);

    //register a pet
    public Pet registerPet(Pet newPet);

    //update pet
    public void updatePet(Pet petToUpdate);

    //delete pet
    public void deletePet(int petId);


}
