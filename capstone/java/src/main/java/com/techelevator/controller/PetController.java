package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(path = "pets/owners/{id}",method = RequestMethod.GET)
    public List<Pet> petsByOwnerId(@PathVariable int id){return petDao.getPetsByOwnerId(id);}





}
