package com.example.pets.presentation.ui.utils;

import android.databinding.ObservableInt;

public class DataBinding {

    public static void inc(ObservableInt number) {
        number.set(number.get() + 1);
    }

    public static void dec(ObservableInt number) {
        number.set(number.get() - 1);
    }
}
