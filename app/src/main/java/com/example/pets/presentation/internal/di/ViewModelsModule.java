package com.example.pets.presentation.internal.di;

import com.example.pets.presentation.ui.bases.BaseViewModel;
import com.example.pets.presentation.ui.screens.main.PetsListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PetsListViewModel.class)
    BaseViewModel bind(PetsListViewModel viewModel);
}
