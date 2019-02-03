package com.example.pets.presentation.ui.screens.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.example.pets.R;
import com.example.pets.databinding.ListContentBinding;
import com.example.pets.presentation.ui.bases.ViewModelFragment;

public class PetsListFragment extends ViewModelFragment<PetsListViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.list_content;
    }

    @Override
    protected Class<PetsListViewModel> getViewModelClass() {
        return PetsListViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        DividerItemDecoration decoration = new DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
        );

        getBinding(ListContentBinding.class).recyclerView.addItemDecoration(decoration);
    }
}
