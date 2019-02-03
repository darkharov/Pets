package com.example.pets.presentation.ui.bases.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pets.BR;

public abstract class SelfBindingFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        ViewDataBinding binding =
                DataBindingUtil.inflate(
                        inflater,
                        getLayoutId(),
                        container,
                        false
                );

        binding.setVariable(BR.viewModel, this);
        return binding.getRoot();
    }
}
