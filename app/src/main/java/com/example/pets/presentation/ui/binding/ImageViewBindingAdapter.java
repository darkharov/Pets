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

        int size = imageView.getResources().getDimensionPixelSize(R.dimen.image_size_item_pet);
        Picasso.get()
                .load(url)
                .resize(size, size)
                .placeholder(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .into(imageView);
    }
}
