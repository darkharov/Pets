package com.example.pets.data;

import com.example.pets.core.NetworkFacade;
import com.example.pets.core.entities.Pet;
import com.example.pets.data.internal.Api;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
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
                .map(this::transform)
                .retry(e -> !(e instanceof RuntimeException));
    }

    private List<Pet> transform(Api.PetsResponse response) {

        return Observable.fromIterable(response.data)
                .zipWith(
                        Observable.range(0, Integer.MAX_VALUE),
                        (entry, index) -> new Pet(index, entry.title, entry.url)
                )
                .toList()
                .blockingGet();
    }
}
