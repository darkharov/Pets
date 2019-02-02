package com.example.pets.presentation.ui.screens.main;

import android.databinding.ObservableField;

import com.example.pets.core.entities.Pet;
import com.example.pets.presentation.ui.bases.BaseViewModel;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class PetsListViewModel extends BaseViewModel {

    public final ObservableField<String> text = new ObservableField<>("");

    @Inject
    PetsListViewModel() {
    }


    @Inject
    void init() {
        execute(
                networkFacade.getPets(Pet.Criteria.CAT),
                new GetPetsObserver()
        );
    }


    private class GetPetsObserver extends BaseSingleObserver<List<Pet>> {

        @Override
        public void onSuccess(List<Pet> pets) {

            String text = String.format(
                    Locale.getDefault(),
                    "The pets list has been loaded: size=%d",
                    pets.size()
            );

            PetsListViewModel.this.text.set(text);
        }
    }
}
