package com.android.codingtask.binding;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class AppBindings {

    @BindingAdapter("isGone")
    public static void setVisibility(View view, boolean isGone) {
        view.setVisibility(isGone ? View.GONE : View.VISIBLE);
    }

    @BindingAdapter("star_count")
    public static void setStarCount(TextView view, int count) {
        view.setText(String.valueOf(count));
    }

    @BindingAdapter("user_avatar")
    public static void setUserAvatar(ImageView view, String url) {
        Picasso.get()
                .load(url)
                .into(view);
    }
}
