package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class   JdbcPetDaoTest extends BaseDaoTests {
    static List<String> personalities = new ArrayList<>();
    static List<String> personalities1 = new ArrayList<>();
    static List<String> personalities2 = new ArrayList<>();

    protected static final Pet PET_1 = new Pet(2001,"Minnie",1003,"dog","Who knows","female","medium",personalities,"Chaos incarnate",true,true);
    protected static final Pet PET_2 = new Pet(2002,"Bob",1003,"cat","He's a cat!","male","small",personalities1,"Eats plants. Is a menace",true,true);
    protected static final Pet PET_3 = new Pet(2003,"DOGGO",1003,"dog","DOG","male","large",personalities2,"test description",true,true);
    private JdbcPetDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcPetDao(jdbcTemplate);
    }

    @Test
    public void get_all_pets_returns_all_pets(){
        List<Pet> pets = sut.getAllPets();
        Assert.assertNotNull(pets);
        Assert.assertEquals(3,pets.size());
        Assert.assertEquals(PET_1,pets.get(0));
        Assert.assertEquals(PET_2,pets.get(1));
        Assert.assertEquals(PET_3,pets.get(2));

    }

    @Test
    public void get_pet_by_id_returns_correct_pet(){
        Pet pet = sut.getPetByPetId(2001);
        Assert.assertEquals(PET_1,pet);
        Pet pet2 = sut.getPetByPetId(2002);
        Assert.assertEquals(PET_2,pet2);
        Pet pet3 = sut.getPetByPetId(2003);
        Assert.assertEquals(PET_3,pet3);

    }

@Test
public void register_pet_creates_pet_and_returns_pet(){
        Pet pet = new Pet(2004,"DOGGO",1003,"dog","DOG","male","large",personalities2,"test description",true,true);
        Pet createdPet = sut.registerPet(pet);
        Assert.assertEquals(pet,createdPet);

}

@Test
public void update_pet_has_expected_values(){
        Pet updatedPet = sut.getPetByPetId(2003);
        updatedPet.setOwnerId(1002);
        updatedPet.setSize("small");

        sut.updatePet(updatedPet,2003);

        Pet testPet = sut.getPetByPetId(2003);
        Assert.assertEquals(updatedPet,testPet);

}




    private void assertPets(Pet pet, Pet otherPet){
        Assert.assertEquals(pet.getPetId(),otherPet.getPetId());
        Assert.assertEquals(pet.getOwnerId(),otherPet.getOwnerId());
        Assert.assertEquals(pet.getPetType(),otherPet.getPetType());
        Assert.assertEquals(pet.getBreed(),otherPet.getBreed());
        Assert.assertEquals(pet.getDescription(),otherPet.getDescription());
        Assert.assertEquals(pet.getGender(),otherPet.getGender());
        Assert.assertEquals(pet.getPetName(),otherPet.getPetName());
        Assert.assertEquals(pet.getIsUpToDateWithVaccinations(),otherPet.getIsUpToDateWithVaccinations());
        Assert.assertEquals(pet.getIsFixed(),otherPet.getIsFixed());
        Assert.assertEquals(pet.getSize(),otherPet.getSize());
        Assert.assertEquals(pet.getPersonalityTypes(),otherPet.getPersonalityTypes());

    }

}