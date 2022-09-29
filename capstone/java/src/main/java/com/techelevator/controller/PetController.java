package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class PetController {

    private PetDao petDao;

    public PetController(PetDao petDao) {
        this.petDao = petDao;
    }

    /**
     * Returns a list of all pets or a filtered list based on the provided parameters.
     * e.g. /pets -> returns all pets
     * e.g. /pets?type=dog returns all dogs
     * e.g. /pets?ownerId=1003&type=cat returns all cats owned by used with the ID 1003
     *
     * @param type the pet type as a String
     * @param ownerId the ID of the pet's owner as an Integer
     * @return a list of pets that match the given parameters
     */
    @RequestMapping(path = "/pets", method = RequestMethod.GET)
    public List<Pet> allPets(@RequestParam(required = false) String type, @RequestParam(required = false) Integer ownerId) {
        List<Pet> pets = petDao.getAllPets();
        if (type != null) {
            pets = pets.stream().filter(pet -> pet.getPetType().equalsIgnoreCase(type)).collect(Collectors.toList());
        }
        if (ownerId != null) {
            pets = pets.stream().filter(pet -> pet.getOwnerId() == ownerId).collect(Collectors.toList());
        }
        return pets;
    }

    /**
     * Returns a single pet with the corresponding petId
     *
     * @param id the petId for the pet as an int
     * @return a single pet with the matching id
     */
    @RequestMapping(path = "/pets/{id}", method = RequestMethod.GET)
    public Pet petsByPetId(@PathVariable int id) {
        return petDao.getPetByPetId(id);
    }

    /**
     * Registers a new pet to the database
     * @param newPet the pet object to add
     * @return the added pet object
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/pets", method = RequestMethod.POST)
    public Pet registerPet(@Valid @RequestBody Pet newPet) //throw exception?
    {
        return petDao.registerPet(newPet);
    }

    /**
     * Updates a pet in the database
     *
     * @param updatedPet the updated pet object to store
     * @param id the id of the pet to update
     */
    @RequestMapping(path = "/pets/{id}", method = RequestMethod.PUT)
    public void updatePet(@RequestBody Pet updatedPet, @PathVariable int id) {
        petDao.updatePet(updatedPet, id);
    }

    /**
     * Deletes a pet with the given id
     *
     * @param id the id of the pet to delete
     */
    @RequestMapping(path = "/pets/{id}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable int id) {
        petDao.deletePet(id);
    }

}
