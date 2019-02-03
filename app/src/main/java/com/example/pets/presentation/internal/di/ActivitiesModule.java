package com.example.pets.presentation.internal.di;

import com.example.pets.presentation.ui.screens.initial.SplashActivity;
import com.example.pets.presentation.ui.screens.main.DashboardActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivitiesModule {
    @ContributesAndroidInjector SplashActivity splash();
    @ContributesAndroidInjector DashboardActivity dashboard();
}
