package com.example.pets.presentation.internal.impls;

import com.example.pets.presentation.ui.CustomViewModelProvider;
import com.example.pets.presentation.ui.bases.BaseViewModel;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactoryImpl implements CustomViewModelProvider.Factory {

    private final Map<Class<? extends BaseViewModel>, Provider<BaseViewModel>> providers;

    @Inject
    public ViewModelFactoryImpl(
            Map<Class<? extends BaseViewModel>, Provider<BaseViewModel>> providers
    ) {
        this.providers = providers;
    }

    @Override
    public BaseViewModel create(Class<? extends BaseViewModel> clazz) {
        return providers.get(clazz).get();
    }
}
