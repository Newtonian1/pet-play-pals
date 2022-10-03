package com.techelevator.model;

import java.util.List;

public class Pet {
    private int petId;
    private int ownerId;
    private String petType;
    private String petName;
    private String breed;
    private String gender;
    private String size;
    private List<String> personalityTypes;
    private String description;
    private boolean isUpToDateWithVaccinations;
    private boolean isFixed;

    public Pet(int petId, String petName, int ownerId, String petType, String breed, String gender, String size, List<String> personalityTypes, String description, boolean isUpToDateWithVaccinations, boolean isFixed) {
        this.petId = petId;
        this.ownerId = ownerId;
        this.petType = petType;
        this.petName = petName;
        this.breed = breed;
        this.gender = gender;
        this.size = size;
        this.personalityTypes = personalityTypes;
        this.description = description;
        this.isUpToDateWithVaccinations = isUpToDateWithVaccinations;
        this.isFixed = isFixed;
    }

    public Pet() {
    }

    public boolean getIsFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getPersonalityTypes() {
        return personalityTypes;
    }

    public void setPersonalityTypes(List<String> personalityTypes) {
        this.personalityTypes = personalityTypes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsUpToDateWithVaccinations() {
        return isUpToDateWithVaccinations;
    }

    public void setUpToDateWithVaccinations(boolean isUpToDateWithVaccinations) {
        this.isUpToDateWithVaccinations = isUpToDateWithVaccinations;
    }
}
