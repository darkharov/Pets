package com.example.pets.presentation.ui.screens.dashboard.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.pets.R;
import com.example.pets.core.entities.Pet;
import com.example.pets.presentation.ui.bases.BaseActivity;
import com.example.pets.presentation.ui.screens.Screens;

import ru.terrakok.cicerone.Navigator;

public class PetDetailsActivity extends BaseActivity {

    private static final String EXTRA_PET = PetDetailsActivity.class.getCanonicalName() + '$' + "EXTRA_PET";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container;
    }

    @Override
    protected Navigator getNavigator() {
        return new NavigatorImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Pet pet = (Pet) getIntent().getSerializableExtra(EXTRA_PET);
            router.replaceScreen(Screens.PET_DETAILS_FORM, pet);
        }
    }

    public static Intent newIntent(Context context, Pet pet) {
        return new Intent(context, PetDetailsActivity.class).putExtra(EXTRA_PET, pet);
    }


    private class NavigatorImpl extends BaseNavigator {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.PET_DETAILS_FORM:
                    return PetDetailsFragment.newInstance((Pet) data);
            }
            return super.createFragment(screenKey, data);
        }
    }
}
