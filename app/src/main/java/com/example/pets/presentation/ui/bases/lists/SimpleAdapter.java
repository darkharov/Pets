package com.example.pets.presentation.ui.bases.lists;

import java.util.Collections;
import java.util.List;

public abstract class SimpleAdapter<I> extends BindingAdapter<I> {

    private List<I> list = Collections.emptyList();

    public void setList(List<I> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    protected final I getItemAt(int position) {
        return list.get(position);
    }

    @Override
    public final int getItemCount() {
        return list.size();
    }
}
