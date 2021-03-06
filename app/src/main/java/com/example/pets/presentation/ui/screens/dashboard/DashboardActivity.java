package com.example.pets.presentation.ui.screens.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.pets.R;
import com.example.pets.core.entities.Pet;
import com.example.pets.presentation.ui.bases.BaseActivity;
import com.example.pets.presentation.ui.screens.Screens;
import com.example.pets.presentation.ui.screens.dashboard.details.PetDetailsActivity;

import java.util.LinkedList;
import java.util.Objects;

import ru.terrakok.cicerone.Navigator;

public class DashboardActivity extends BaseActivity {

    private static final String TAB_STACK = "TAB_STACK";

    private TabLayout tabLayout;
    private LinkedList<Pet.Criteria> stack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected Navigator getNavigator() {
        return new NavigatorImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initStack(savedInstanceState);
        setupTabLayout();
    }

    @SuppressWarnings("unchecked")
    private void initStack(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {

            stack = new LinkedList<>();
            Pet.Criteria first = Pet.Criteria.values()[0];

            stack.push(first);
            replace(first);

        } else {

            stack = (LinkedList<Pet.Criteria>) savedInstanceState.getSerializable(TAB_STACK);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TAB_STACK, stack);
    }

    @Override
    public void onBackPressed() {

        if (stack.size() > 1) {
            pop(stack.pop());
            syncTabs();
        } else {
            finish();
        }
    }

    private void setupTabLayout() {
        tabLayout = findViewById(R.id.tab_layout);

        for (Pet.Criteria value : Pet.Criteria.values()) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setTag(value);
            tabLayout.addTab(tab);
            tab.setText(value.name());
        }

        syncTabs();
        tabLayout.addOnTabSelectedListener(new OnTabSelectedListenerImpl());
    }

    private void syncTabs() {
        int tabIndex = stack.element().ordinal();
        Objects.requireNonNull(tabLayout.getTabAt(tabIndex)).select();
    }

    private void showPetList(Pet.Criteria criteria) {
        if (stack.contains(criteria)) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            while (stack.getFirst() != criteria) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(stack.pop().name());
                transaction.detach(fragment);
            }

            transaction.commit();

        } else {
            stack.push(criteria);
            add(criteria);
        }
    }

    private void pop(Pet.Criteria criteria) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(criteria.name());

        getSupportFragmentManager().beginTransaction()
                .detach(Objects.requireNonNull(fragment))
                .commit();
    }

    private void add(Pet.Criteria criteria) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(criteria.name());

        if (fragment == null) {
            transaction.add(
                    R.id.fragment_container,
                    PetsListFragment.newInstance(criteria),
                    criteria.name()
            );
        } else {
            transaction.attach(fragment);
        }

        transaction.commit();
    }

    private void replace(Pet.Criteria criteria) {
        getSupportFragmentManager().beginTransaction()
                .replace(
                        R.id.fragment_container,
                        PetsListFragment.newInstance(criteria),
                        criteria.name()
                )
                .commit();
    }


    private class OnTabSelectedListenerImpl implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Pet.Criteria criteria = Objects.requireNonNull((Pet.Criteria) tab.getTag());
            showPetList(criteria);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    }


    private class NavigatorImpl extends BaseNavigator {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            Pet.Criteria criteria = Pet.Criteria.valueOf(screenKey);
            return PetsListFragment.newInstance(criteria);
        }

        @Override
        protected Intent createActivityIntent(Context context, String screenKey, Object data) {

            switch (screenKey) {

                case Screens.PET_DETAILS:
                    return PetDetailsActivity.newIntent(DashboardActivity.this, (Pet) data);

                default:
                    return super.createActivityIntent(context, screenKey, data);

            }
        }
    }
}
