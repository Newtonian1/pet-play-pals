package com.techelevator.dao;

import com.techelevator.model.PetPicture;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcPetPictureDao implements PetPictureDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPetPictureDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PetPicture> getPetPictures() {
        return null;
    }

    @Override
    public PetPicture getPetPictureByPetId(int id) {
        return null;
    }

    @Override
    public boolean updatePetPictureByPetId(PetPicture petPicture, int id) {
        return false;
    }

    @Override
    public Integer addPetPicture(PetPicture petPicture) {
        String sql = "INSERT INTO pet_picture (pet_id, pet_picture) VALUES (?,?) RETURNING pet_id;";
        return jdbcTemplate.queryForObject(sql, Integer.class, petPicture.getPetId(), petPicture.getPetPicture());
    }

    @Override
    public boolean deletePetPictureByPetId(int id) {
        return false;
    }
}
