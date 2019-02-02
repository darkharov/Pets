package com.example.pets.presentation.ui;

import com.example.pets.presentation.ui.bases.BaseViewModel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomViewModelProvider {

    private final Factory factory;
    private final Map<Class<? extends BaseViewModel>, Family> families;

    @Inject
    public CustomViewModelProvider(Factory factory) {
        families = new HashMap<>();
        this.factory = factory;
    }

    @SuppressWarnings("unchecked")
    public <VM extends BaseViewModel> VM get(
            Class<? extends BaseViewModel> clazz,
            Object key
    ) {

        Family family = families.get(clazz);

        if (family == null) {
            family = new Family(clazz);
            families.put(clazz, family);
        }

        return (VM) family.get(key);
    }

    public void destroy(Class<? extends BaseViewModel> clazz, Object key) {
        families.get(clazz).removeAndDestroy(key);
    }


    public interface Factory {
        BaseViewModel create(Class<? extends BaseViewModel> clazz);
    }


    private class Family {

        private final Class<? extends BaseViewModel> clazz;
        private final Map<Object, BaseViewModel> viewModels;

        private Family(Class<? extends BaseViewModel> clazz) {
            this.clazz = clazz;
            viewModels = new HashMap<>();
        }

        private BaseViewModel get(Object key) {
            BaseViewModel viewModel = viewModels.get(key);

            if (viewModel == null) {
                viewModel = factory.create(clazz);
                viewModels.put(key, viewModel);
            }

            return viewModel;
        }

        private void removeAndDestroy(Object key) {
            viewModels.remove(key).onDestroy();
        }
    }
}
