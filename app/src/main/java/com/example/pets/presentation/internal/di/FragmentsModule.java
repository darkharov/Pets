package com.example.pets.presentation.internal.di;

import com.example.pets.presentation.ui.screens.dashboard.PetsListFragment;
import com.example.pets.presentation.ui.screens.dashboard.details.PetDetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentsModule {
    @ContributesAndroidInjector PetsListFragment petsList();
    @ContributesAndroidInjector PetDetailsFragment petDetails();
}
