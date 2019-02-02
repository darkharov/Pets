package com.example.pets.data;

import com.example.pets.core.NetworkFacade;
import com.example.pets.core.entities.Pet;
import com.example.pets.data.internal.Api;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class NetworkFacadeImpl implements NetworkFacade {

    private final Api api;

    @Inject
    public NetworkFacadeImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<List<Pet>> getPets(Pet.Criteria criteria) {
        return api.pets(criteria.name().toLowerCase())
                .map((response) -> response.data);
    }
}
