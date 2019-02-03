package com.example.pets.presentation.ui.binding;


import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.example.pets.R;
import com.squareup.picasso.Picasso;

public class ImageViewBindingAdapter {

    @BindingAdapter("url")
    public static void setImageUrl(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageDrawable(null);
            return;
        }

        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);
    }
}
