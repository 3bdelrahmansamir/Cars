package com.softxpert.task.presentation.base;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OnScrollListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager linearLayoutManager;
    public OnLoadMoreListener onLoadMoreListener;


    public OnScrollListener(LinearLayoutManager linearLayoutManager, OnLoadMoreListener onLoadMoreListener) {
        this.linearLayoutManager = linearLayoutManager;
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int totalItemCount = linearLayoutManager.getItemCount();
        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

        if (totalItemCount == (lastVisibleItem + 1)) {
            if (onLoadMoreListener != null) {
                onLoadMoreListener.onLoadMore();
            }
        }
    }
}
