package com.example.pets.presentation.internal.di;

import com.example.pets.presentation.ui.screens.main.PetsListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentsModule {
    @ContributesAndroidInjector PetsListFragment petsList();
}
