package com.example.pets.core.entities;

import java.io.Serializable;

public class Pet implements Serializable {

    private final long id;
    private final String name;
    private final String imageUrl;

    public Pet(long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public enum Criteria {
        CAT, DOG
    }
}
