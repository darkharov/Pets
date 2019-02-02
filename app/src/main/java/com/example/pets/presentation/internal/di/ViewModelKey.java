package com.example.pets.presentation.internal.di;

import com.example.pets.presentation.ui.bases.BaseViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

@Target(value = {ElementType.METHOD})
@MapKey
@interface ViewModelKey {
    Class<? extends BaseViewModel> value();
}
