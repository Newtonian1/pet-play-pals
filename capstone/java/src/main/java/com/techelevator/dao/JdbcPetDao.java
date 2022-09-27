package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcPetDao implements PetDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> allPets = new ArrayList<>();
        String sql = "SELECT * FROM pets";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Pet pet = mapRowToPet(results);
            allPets.add(pet);

        }
        return allPets;
    }

    @Override
    public Pet getPetByPetId(int petId) {
        String sql = "SELECT * FROM pets WHERE pet_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId);
        if (results.next()) {
            return mapRowToPet(results);
        } else {
            return null; //should we throw an exception?
        }
    }

    @Override
    public List<Pet> getPetsByType(String petType) {
        List<Pet> petsByType = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE pet_type =?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petType);
        while (results.next()) {
            Pet pet = mapRowToPet(results);
            petsByType.add(pet);
        }
        return petsByType;
    }


    @Override
    public List<Pet> getPetsByOwnerId(int ownerId) {
        List<Pet> petsByUserId = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE owner_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ownerId);
        while (results.next()) {
            Pet pet = mapRowToPet(results);
            petsByUserId.add(pet);
        }

        return petsByUserId;
    }

    @Override
    public List<Pet> getPetsByPersonalityType(String personality) { //double check this later
        List<Pet> petsByPersonality = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE personality_type =?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, personality);
        while (results.next()) {
            Pet pet = mapRowToPet(results);
            petsByPersonality.add(pet);
        }
        return petsByPersonality;
    }

    @Override
    public List<Pet> getPetsByBreed(String breed) {
        List<Pet> petsByBreed = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE breed =?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,breed);
        while (results.next()){
            Pet pet = mapRowToPet(results);
            petsByBreed.add(pet);
        }

        return petsByBreed;
    }

    @Override
    public Pet registerPet(Pet newPet) { //TODO this needs checked
        String sql = "INSERT INTO pets (owner_id,pet_type,breed,gender,pet_name,personality_type,size,description,is_fixed,is_up_to_date_with_vaccinations) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Integer newPetId = jdbcTemplate.query(sql, Integer.class, newPet.getOwnerId(), newPet.getPetType(), newPet.getBreed(), newPet.getGender(), newPet.getPetName(), newPet.getPersonalityType(), newPet.getSize(), newPet.getDescription(), newPet.getIsFixed(), newPet.getIsUpToDateWithVaccinations());
        return getPetByPetId(newPetId);
    }

    @Override
    public void updatePet(Pet petToUpdate) {
        String sql = "UPDATE pets SET owner_id=?,pet_type=?,breed=?,gender=?,pet_name=?,personality_type=?,size=?,description=?,is_fixed=?,is_up_to_date_with_vaccinations=? WHERE pet_id=?";
        jdbcTemplate.update(sql, petToUpdate.getOwnerId(), petToUpdate.getPetType(), petToUpdate.getBreed(), petToUpdate.getGender(), petToUpdate.getPetName(), petToUpdate.getPersonalityType(), petToUpdate.getSize(), petToUpdate.getDescription(), petToUpdate.getIsFixed(), petToUpdate.getIsUpToDateWithVaccinations());
    }

    @Override
    public void deletePet(int petId) {
        String sql = "DELETE FROM pets WHERE pet_id = ?";
        jdbcTemplate.update(sql, petId);

    }

    private Pet mapRowToPet(SqlRowSet sql) {
        Pet pet = new Pet();
        pet.setPetId(sql.getInt("pet_id"));
        pet.setOwnerId(sql.getInt("owner_id"));
        pet.setPetType(sql.getString("pet_type"));
        pet.setBreed(sql.getString("breed"));
        pet.setGender(sql.getString("gender"));
        //pet.setPersonalityType(sql.getString("personality_type"));
        pet.setPetName(sql.getString("pet_name"));
        pet.setSize(sql.getString("size"));
        pet.setDescription(sql.getString("description"));
        pet.setUpToDateWithVaccinations(Boolean.parseBoolean(sql.getString("is_up_to_date_with_vaccinations")));
        pet.setFixed(sql.getBoolean("is_fixed"));
        return pet;
    }

}
