package com.example.pets.presentation.ui.screens.initial;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.pets.presentation.ui.bases.BaseActivity;
import com.example.pets.presentation.ui.screens.Screens;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        router.replaceScreen(Screens.DASHBOARD);
    }
}
