package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcPetDao implements PetDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcPetDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Pet> getAllPets() {
        List<Pet> allPets = new ArrayList<>();
        String sql = "SELECT * FROM pets";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Pet pet = mapRowToPet(results);
            allPets.add(pet);

        }
        return allPets;
    }

    @Override
    public Pet getPetByPetId(int petId) {
        String sql = "SELECT * FROM pets WHERE pet_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,petId);
        if (results.next()){
            return mapRowToPet(results);
        } else {
            return null; //should we throw an exception?
        }
    }

    @Override
    public List<Pet> getPetsByType(String petType) {
        List<Pet> petsByType = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE pet_type =?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,petType);
        while(results.next()){
            Pet pet = mapRowToPet(results);
            petsByType.add(pet);
        }
        return petsByType;
    }

    @Override
    public List<Pet> getPetsByUserId(int userId) {
        return null;
    }

    @Override
    public List<Pet> getPetsByPersonalityType(String personality) {
        return null;
    }

    @Override
    public List<Pet> getPetsByBreed(String breed) {
        return null;
    }

    @Override
    public Pet registerPet(Pet newPet) {
        return null;
    }

    @Override
    public void updatePet(Pet petToUpdate) {

    }

    @Override
    public void deletePet(Pet petToDelete) {
        String sql = "DELETE FROM pets WHERE pet_id = ? AND user_id=?"; //not sure if user id will be needed
        jdbcTemplate.update(sql,petToDelete);

    }

    private Pet mapRowToPet(SqlRowSet sql){
        Pet pet = new Pet();
        pet.setPetId(sql.getInt("pet_id"));
        pet.setUserId(sql.getInt("user_id"));
        pet.setPetType(sql.getString("pet_type"));
        pet.setBreed(sql.getString("breed"));
        pet.setGender(sql.getString("gender"));
        pet.setPersonalityType(sql.getString("personality_type"));
        pet.setPetName(sql.getString("pet_name"));
        pet.setSize(sql.getString("size"));
        pet.setDescription(sql.getString("description"));
       // pet.setUpToDateWithVaccinations(sql.getString("")); //TODO get DB column name
        return pet;
    }

}
