package com.example.pets.presentation.ui.binding;

import android.databinding.BindingConversion;
import android.view.View;

public class BindingConversions {

    @BindingConversion
    public static int toVisibility(Boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }
}
