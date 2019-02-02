package com.example.pets.core.entities;

public class Pet {

    private final String title;
    private final String imageUrl;

    public Pet(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public enum Criteria {
        CAT, DOG
    }
}
