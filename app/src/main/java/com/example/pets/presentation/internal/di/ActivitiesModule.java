package com.example.pets.presentation.internal.di;

import com.example.pets.presentation.ui.screens.dashboard.DashboardActivity;
import com.example.pets.presentation.ui.screens.dashboard.details.PetDetailsActivity;
import com.example.pets.presentation.ui.screens.initial.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivitiesModule {
    @ContributesAndroidInjector SplashActivity splash();
    @ContributesAndroidInjector DashboardActivity dashboard();
    @ContributesAndroidInjector PetDetailsActivity petDetails();
}
