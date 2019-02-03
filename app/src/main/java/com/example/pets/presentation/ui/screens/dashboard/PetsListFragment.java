package com.example.pets.presentation.ui.screens.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.example.pets.R;
import com.example.pets.core.entities.Pet;
import com.example.pets.databinding.ListContentBinding;
import com.example.pets.presentation.ui.bases.fragments.ViewModelFragment;

import java.util.Objects;

public class PetsListFragment extends ViewModelFragment<PetsListViewModel> {

    private static final String ARG_PET_CRITERIA_ORDINAL = "ARG_PET_CRITERIA_ORDINAL";

    @Override
    protected int getLayoutId() {
        return R.layout.list_content;
    }

    @Override
    protected Class<PetsListViewModel> getViewModelClass() {
        return PetsListViewModel.class;
    }

    @Nullable
    @Override
    protected Object getViewModelKey() {
        return getCriteria();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getViewModel().init(getCriteria());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        DividerItemDecoration decoration = new DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
        );

        getBinding(ListContentBinding.class).recyclerView.addItemDecoration(decoration);
    }

    private Pet.Criteria getCriteria() {
        int ordinal = Objects.requireNonNull(getArguments()).getInt(ARG_PET_CRITERIA_ORDINAL);
        return Pet.Criteria.values()[ordinal];
    }


    public static PetsListFragment newInstance(Pet.Criteria criteria) {
        Bundle args = new Bundle();
        args.putInt(ARG_PET_CRITERIA_ORDINAL, criteria.ordinal());

        PetsListFragment fragment = new PetsListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
