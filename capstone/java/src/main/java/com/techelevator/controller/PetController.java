package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     *
     * @param type the pet type to return as a String
     * @return a list of pets that match the given parameters
     */
    @RequestMapping(path = "/pets", method = RequestMethod.GET)
    public List<Pet> allPets(@RequestParam(required = false) String type) {
        if (type != null) {
            return petDao.getAllPets().stream().filter(pet -> pet.getPetType().equalsIgnoreCase(type)).collect(Collectors.toList());
        } else {
            return petDao.getAllPets();
        }
    }

    //return pets by the pet id
    @RequestMapping(path = "/pets/{id}", method = RequestMethod.GET)
    public Pet petsByPetId(@PathVariable int id) {
        return petDao.getPetByPetId(id);
    }

    // return list of pets from ownerid
    @RequestMapping(path = "/pets/owners/{id}", method = RequestMethod.GET) // path?
    public List<Pet> petsByOwnerId(@PathVariable int id) {
        return petDao.getPetsByOwnerId(id);
    }

    //return list of pets by type
    @RequestMapping(path = "/pets/type/{id}", method = RequestMethod.GET) //might not be accurate
    public List<Pet> petsByType(@PathVariable String id) {
        return petDao.getPetsByPersonalityType(id);
    }

    //list of pets by breed
    @RequestMapping(path = "/pets/breed/{id}", method = RequestMethod.GET) //path?
    public List<Pet> petsByBreed(@PathVariable String id) {
        return petDao.getPetsByBreed(id);
    }

//TODO personality type

    //register a pet
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/pets", method = RequestMethod.POST) //path?
    public Pet registerPet(@Valid @RequestBody Pet newPet) //throw exception?
    {
        return petDao.registerPet(newPet);
    }


    //update a pet
    @RequestMapping(path = "/pets/{id}", method = RequestMethod.PUT)
    public void updatePet(@RequestBody Pet updatedPet, @PathVariable int id) {
        petDao.updatePet(updatedPet, id);
    }

    //delete a pet
    @RequestMapping(path = "/pets/{id}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable int id) {
        petDao.deletePet(id);
    }

}
