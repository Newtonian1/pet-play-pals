package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPetDao implements PetDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> allPets = new ArrayList<>();
        String sql = "SELECT p.pet_id, p.pet_name, p.owner_id, pt.pet_type, p.breed, pg.gender, ps.pet_size, p.is_fixed, p.is_up_to_date_with_vaccinations, p.pet_description\n" +
                "FROM pets p\n" +
                "JOIN pet_types pt ON pt.pet_type_id = p.pet_type_id\n" +
                "JOIN pet_genders pg ON pg.gender_id = p.gender_id\n" +
                "JOIN pet_sizes ps ON ps.pet_size_id = p.size_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Pet pet = mapRowToPet(results);
            allPets.add(pet);
        }
        return allPets;
    }

    @Override
    public Pet getPetByPetId(int petId) {
        String sql = "SELECT p.pet_id, p.pet_name, p.owner_id, pt.pet_type, p.breed, pg.gender, ps.pet_size, p.is_fixed, p.is_up_to_date_with_vaccinations, p.pet_description\n" +
                "FROM pets p\n" +
                "JOIN pet_types pt ON pt.pet_type_id = p.pet_type_id\n" +
                "JOIN pet_genders pg ON pg.gender_id = p.gender_id\n" +
                "JOIN pet_sizes ps ON ps.pet_size_id = p.size_id\n" +
                "WHERE p.pet_id = ?;";
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
        String sql = "SELECT p.pet_id, p.pet_name, p.owner_id, pt.pet_type, p.breed, pg.gender, ps.pet_size, p.is_fixed, p.is_up_to_date_with_vaccinations, p.pet_description\n" +
                "FROM pets p\n" +
                "JOIN pet_types pt ON pt.pet_type_id = p.pet_type_id\n" +
                "JOIN pet_genders pg ON pg.gender_id = p.gender_id\n" +
                "JOIN pet_sizes ps ON ps.pet_size_id = p.size_id\n" +
                "WHERE pt.pet_type = ?;";
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
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breed);
        while (results.next()) {
            Pet pet = mapRowToPet(results);
            petsByBreed.add(pet);
        }

        return petsByBreed;
    }

    @Override
    public Pet registerPet(Pet newPet) {
        String sql = "INSERT INTO pets \n" +
                "\t(pet_name,\n" +
                "\t owner_id,\n" +
                "\t pet_type_id,\n" +
                "\t breed,\n" +
                "\t gender_id,\n" +
                "\t size_id,\n" +
                "\t is_fixed,\n" +
                "\t is_up_to_date_with_vaccinations,\n" +
                "\t pet_description) \n" +
                "VALUES \n" +
                "\t(?,\n" +
                "\t ?,\n" +
                "\t (SELECT pet_type_id FROM pet_types WHERE pet_type = ?),\n" +
                "\t ?,\n" +
                "\t (SELECT gender_id FROM pet_genders\tWHERE gender = ?),\n" +
                "\t (SELECT pet_size_id FROM pet_sizes\tWHERE pet_size = ?),\n" +
                "\t ?,\n" +
                "\t ?,\n" +
                "\t ?)\n" +
                "RETURNING pet_id;";
        Integer newPetId = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                newPet.getPetName(),
                newPet.getOwnerId(),
                newPet.getPetType(),
                newPet.getBreed(),
                newPet.getGender(),
                newPet.getSize(),
                newPet.getIsFixed(),
                newPet.getIsUpToDateWithVaccinations(),
                newPet.getDescription()
        );
        if (newPetId != null) {
            return getPetByPetId(newPetId);
        } else {
            return null; // throw exception?
        }
    }

    @Override
    public void updatePet(Pet petToUpdate, int id) {
        String sql = "UPDATE pets\n" +
                "SET pet_name = ?, \n" +
                "\towner_id = ?,\n" +
                "\tpet_type_id = (SELECT pet_type_id \n" +
                "\t\t\t\tFROM pet_types\n" +
                "\t\t\t\tWHERE pet_type = ?), \n" +
                "\tbreed = ?, \n" +
                "\tgender_id = (SELECT gender_id \n" +
                "\t\t\t\tFROM pet_genders\n" +
                "\t\t\t\tWHERE gender = ?), \n" +
                "\tsize_id = (SELECT pet_size_id \n" +
                "\t\t\t\tFROM pet_sizes\n" +
                "\t\t\t\tWHERE pet_size = ?), \n" +
                "\tis_fixed = ?, \n" +
                "\tis_up_to_date_with_vaccinations = ?, \n" +
                "\tpet_description = ?\n" +
                "WHERE pet_id = ?;";
        jdbcTemplate.update(
                sql,
                petToUpdate.getPetName(),
                petToUpdate.getOwnerId(),
                petToUpdate.getPetType(),
                petToUpdate.getBreed(),
                petToUpdate.getGender(),
                petToUpdate.getSize(),
                petToUpdate.getIsFixed(),
                petToUpdate.getIsUpToDateWithVaccinations(),
                petToUpdate.getDescription(),
                id
        );
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
        pet.setSize(sql.getString("pet_size"));
        pet.setDescription(sql.getString("pet_description"));
        pet.setUpToDateWithVaccinations(sql.getBoolean("is_up_to_date_with_vaccinations"));
        pet.setFixed(sql.getBoolean("is_fixed"));
        return pet;
    }

}
