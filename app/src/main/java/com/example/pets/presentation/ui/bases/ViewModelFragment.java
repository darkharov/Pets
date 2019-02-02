package com.example.pets.presentation.ui.bases;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pets.BR;
import com.example.pets.R;
import com.example.pets.databinding.PreloaderBinding;
import com.example.pets.presentation.ui.CustomViewModelProvider;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

public abstract class ViewModelFragment<VM extends BaseViewModel> extends BaseFragment {

    @Inject CustomViewModelProvider viewModelProvider;

    private VM viewModel;
    private final Set<ViewDataBinding> bindings = new HashSet<>();

    protected abstract Class<VM> getViewModelClass();

    protected Object getViewModelKey() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        PreloaderBinding preloader = safeInflate(inflater, R.layout.preloader, container);
        ViewDataBinding content = safeInflate(inflater, getLayoutId(), container);

        preloader.content.addView(content.getRoot());

        return preloader.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        for (ViewDataBinding binding : bindings) {
            try {
                binding.unbind();
                binding.setVariable(BR.viewModel, null);
            } catch (Throwable ignored) {
                // no op
            }
        }

        bindings.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Activity activity = getActivity();
        boolean changeConfig = activity != null && activity.isChangingConfigurations();

        if (!changeConfig) {
            viewModelProvider.destroy(getViewModelClass(), getViewModelKey());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = viewModelProvider.get(getViewModelClass(), getViewModelKey());
    }

    public final VM getViewModel() {
        return viewModel;
    }

    private <VDB extends ViewDataBinding> VDB safeInflate(
            LayoutInflater inflater,
            @LayoutRes int layoutId,
            @Nullable ViewGroup parent
    ) {
        VDB binding = DataBindingUtil.inflate(
                inflater,
                layoutId,
                parent,
                false
        );
        bindings.add(binding);
        binding.setVariable(BR.viewModel, viewModel);
        return binding;
    }
}
