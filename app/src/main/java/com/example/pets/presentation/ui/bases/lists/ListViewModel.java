package com.example.pets.presentation.ui.bases.lists;

import android.databinding.ObservableInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.example.pets.R;
import com.example.pets.presentation.ui.bases.BaseViewModel;

import java.util.List;

public abstract class ListViewModel<I> extends BaseViewModel {

    public final SimpleAdapter<I> adapter = new AdapterImpl();
    public final ObservableInt noItemsMessage = new ObservableInt();

    @LayoutRes
    protected abstract int getItemLayoutId();

    protected abstract BindingAdapter.BindingViewHolder<I> createViewHolder(View view);

    @StringRes
    protected int getEmptyListMessageId() {
        return R.string.message_no_items;
    }

    @StringRes
    protected int getErrorMessageId() {
        return R.string.message_loading_error;
    }

    public final void notifyListUpdated(List<I> list) {
        adapter.setList(list);
        noItemsMessage.set(list.isEmpty() ? getEmptyListMessageId() : 0);
    }

    public final void notifyListUpdateFailed() {
        if (adapter.getItemCount() == 0) {
            noItemsMessage.set(getErrorMessageId());
        }
    }


    private class AdapterImpl extends SimpleAdapter<I> {

        @Override
        protected int getLayoutId() {
            return getItemLayoutId();
        }

        @Override
        protected BindingViewHolder<I> onCreateViewHolder(View view) {
            return ListViewModel.this.createViewHolder(view);
        }
    }
}
