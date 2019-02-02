package com.example.pets.presentation.ui.screens.main;

import com.example.pets.R;
import com.example.pets.presentation.ui.bases.ViewModelFragment;

public class PetsListFragment extends ViewModelFragment<PetsListViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pets;
    }

    @Override
    protected Class<PetsListViewModel> getViewModelClass() {
        return PetsListViewModel.class;
    }
}
