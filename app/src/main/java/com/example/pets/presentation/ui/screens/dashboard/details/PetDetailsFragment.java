package com.example.pets.presentation.ui.screens.dashboard.details;

import android.os.Bundle;

import com.example.pets.R;
import com.example.pets.core.entities.Pet;
import com.example.pets.presentation.ui.bases.fragments.SelfBindingFragment;

import java.util.Objects;

public class PetDetailsFragment extends SelfBindingFragment {

    private static final String ARG_PET = "ARG_PET";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pet_details;
    }

    public Pet getPet() {
        return (Pet) Objects.requireNonNull(getArguments()).getSerializable(ARG_PET);
    }

    public static PetDetailsFragment newInstance(Pet pet) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PET, pet);

        PetDetailsFragment fragment = new PetDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
