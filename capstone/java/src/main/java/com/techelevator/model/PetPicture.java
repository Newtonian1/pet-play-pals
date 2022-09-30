package com.techelevator.model;

import java.io.File;

public class PetPicture {
    private int petId;
    private byte[] petPicture;

    public PetPicture() {
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public byte[] getPetPicture() {
        return petPicture;
    }

    public void setPetPicture(byte[] petPicture) {
        this.petPicture = petPicture;
    }
}
