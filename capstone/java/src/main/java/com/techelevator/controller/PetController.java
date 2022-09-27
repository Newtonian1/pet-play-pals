package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class PetController {

    private PetDao petDao;

    public PetController(PetDao petDao){
        this.petDao = petDao;
    }
    // return all pets
    @RequestMapping(path = "/pets", method = RequestMethod.GET)
    public List<Pet> allPets(){return petDao.getAllPets();}

//return pets by the pet id
    @RequestMapping(path="/pets/{id}", method = RequestMethod.GET)
    public Pet petsByPetId(@PathVariable int id){return petDao.getPetByPetId(id);
    }

// return list of pets from ownerid
    @RequestMapping(path = "/pets/owners/{id}",method = RequestMethod.GET) // path?
    public List<Pet> petsByOwnerId(@PathVariable int id){return petDao.getPetsByOwnerId(id);}

//return list of pets by type
    @RequestMapping(path = "/pets/type/{id}", method = RequestMethod.GET) //might not be accurate
    public List<Pet> petsByType(@PathVariable String id){return petDao.getPetsByPersonalityType(id);}

//list of pets by breed
    @RequestMapping(path = "/pets/breed/{id}", method = RequestMethod.GET) //path?
    public List<Pet> petsByBreed(@PathVariable String id){return petDao.getPetsByBreed(id);}

//TODO personality type

//register a pet
@ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/register/pet", method = RequestMethod.POST) //path?
    public Pet registerPet(@Valid @RequestBody Pet newPet) //throw exception?
{return petDao.registerPet(newPet);}


//update a pet
    @RequestMapping(path="/update/pet",method = RequestMethod.PUT)
    public void updatePet(@RequestBody Pet updatedPet){
        petDao.updatePet(updatedPet);
    }

//delete a pet
@RequestMapping(path="/pet/delete/{id}",method = RequestMethod.DELETE )
    public void deletePet(@PathVariable int id){
        petDao.deletePet(id);
}

}
