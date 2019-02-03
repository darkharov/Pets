package com.example.pets.presentation.ui.bases;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.pets.R;
import com.example.pets.presentation.ui.screens.Screens;
import com.example.pets.presentation.ui.screens.dashboard.DashboardActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportAppNavigator;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject protected NavigatorHolder navigatorHolder;
    @Inject protected Router router;

    protected Navigator getNavigator() {
        return new BaseNavigator();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(getNavigator());
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }

    @LayoutRes
    protected int getLayoutId() {
        return 0;
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
        }
    }


    protected class BaseNavigator extends SupportAppNavigator {

        public BaseNavigator() {
            super(BaseActivity.this, R.id.fragment_container);
        }

        @Override
        protected Intent createActivityIntent(Context context, String screenKey, Object data) {

            switch (screenKey) {

                case Screens.DASHBOARD:
                    return new Intent(BaseActivity.this, DashboardActivity.class);
            }

            return null;
        }

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            return null;
        }
    }
}
