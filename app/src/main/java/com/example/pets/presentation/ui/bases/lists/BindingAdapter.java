package com.example.pets.presentation.ui.bases.lists;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

public abstract class BindingAdapter<I> extends RecyclerView.Adapter<BindingAdapter.BindingViewHolder<I>> {

    protected abstract int getLayoutId();

    protected abstract I getItemAt(int position);

    protected BindingViewHolder<I> onCreateViewHolder(View view) {
        return new BindingViewHolder<>(view);
    }

    @NonNull
    @Override
    public final BindingViewHolder<I> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                getLayoutId(),
                viewGroup,
                false
        );

        BindingViewHolder<I> viewHolder = onCreateViewHolder(binding.getRoot());
        binding.setVariable(BR.viewModel, viewHolder);

        return viewHolder;
    }

    @Override
    public final void onBindViewHolder(@NonNull BindingViewHolder<I> bindingViewHolder, int itemPosition) {
        bindingViewHolder.item.set(getItemAt(itemPosition));
    }


    public static class BindingViewHolder<I> extends RecyclerView.ViewHolder {

        public final ObservableField<I> item = new ObservableField<>();

        public BindingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
