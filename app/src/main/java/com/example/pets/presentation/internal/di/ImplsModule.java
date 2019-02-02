package com.example.pets.presentation.internal.di;

import com.example.pets.core.NetworkFacade;
import com.example.pets.data.NetworkFacadeImpl;
import com.example.pets.presentation.internal.impls.ViewModelFactoryImpl;
import com.example.pets.presentation.ui.CustomViewModelProvider;

import dagger.Binds;
import dagger.Module;

@Module
public interface ImplsModule {
    @Binds NetworkFacade networkFacade(NetworkFacadeImpl networkFacade);
    @Binds CustomViewModelProvider.Factory viewModelFactory(ViewModelFactoryImpl factory);
}
