package com.example.pets.presentation.internal.di;

import com.example.pets.presentation.ui.screens.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivitiesModule {
    @ContributesAndroidInjector MainActivity main();
}
