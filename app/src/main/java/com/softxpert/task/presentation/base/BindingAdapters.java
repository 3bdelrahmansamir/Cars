package com.softxpert.task.presentation.base;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.softxpert.task.R;

import rx.functions.Action1;

public class BindingAdapters {

    @BindingAdapter("imageURL")
    public static void bindImageURL(@NonNull ImageView view, @Nullable String url) {
        if (url != null) {
            Glide.with(view.getContext())
                    .load(url)
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher)
                            .centerCrop()
                    )
                    .into(view);
        }
    }


    @BindingAdapter("onUserSwipe")
    public static void setOnUserSwipedListener(SwipeRefreshLayout swipeRefreshLayout, Action1<Void> action) {
        swipeRefreshLayout.setOnRefreshListener(() -> action.call(null));
    }

}
