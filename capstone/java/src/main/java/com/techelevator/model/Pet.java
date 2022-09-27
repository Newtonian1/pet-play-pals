package com.techelevator.model;

public class Pet {
    private int petId;
    private int ownerId;
    private String petType; //assume this is for more than just dogs?
    private String petName;
    private String breed;
    private String gender;
    private String size;
    private String[] personalityType;
    private String description; //for the profile of the pet? might not be needed
    private boolean IsUpToDateWithVaccinations;
    private boolean isFixed;

   public boolean getIsFixed(){
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

    public String[] getPersonalityType() {
        return personalityType;
    }

    public void setPersonalityType(String[] personalityType) {
        this.personalityType = personalityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsUpToDateWithVaccinations() {
        return IsUpToDateWithVaccinations;
    }

    public void setUpToDateWithVaccinations(boolean upToDateWithVaccinations) {
        IsUpToDateWithVaccinations = upToDateWithVaccinations;
    }
}
