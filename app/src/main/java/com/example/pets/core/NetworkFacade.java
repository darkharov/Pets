package com.example.pets.core;

import com.example.pets.core.entities.Pet;

import java.util.List;

import io.reactivex.Single;


public interface NetworkFacade {

    Single<List<Pet>> getPets(Pet.Criteria criteria);
}
