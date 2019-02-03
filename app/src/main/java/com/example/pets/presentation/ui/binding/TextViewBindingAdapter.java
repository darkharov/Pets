package com.example.pets.presentation.ui.binding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

public class TextViewBindingAdapter {

    @BindingAdapter("optionalTextRes")
    public static void setOptionalText(TextView textView, int optionalTextRes) {

        CharSequence text = (optionalTextRes == 0)
                ? ""
                : textView.getContext().getText(optionalTextRes);

        textView.setText(text);
    }
}
