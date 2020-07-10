package com.softxpert.task.presentation.ui.show_cars_list;


import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.softxpert.task.R;
import com.softxpert.task.databinding.ActivityCarsBinding;
import com.softxpert.task.presentation.base.BaseActivity;
import com.softxpert.task.presentation.base.OnLoadMoreListener;
import com.softxpert.task.presentation.base.OnScrollListener;

public class CarsActivity extends BaseActivity<ActivityCarsBinding, CarsListViewModel> implements
        CarsListNavigator, OnLoadMoreListener {

    private CarsListViewModel mViewModel;
    private CarsAdapter mAdapter;
    OnScrollListener onScrollListener;
    int page = 1;
    boolean lastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CarsListViewModel.class);
        getViewDataBinding().setVm(mViewModel);
        mViewModel.setNavigator(this);
        subscribeToLiveData();
        initRecyclerView();
        mViewModel.getAllCarsByPage(1);
        getViewDataBinding().srReloadData.setRefreshing(true);
    }

    private void subscribeToLiveData() {

        mViewModel.progressDialogLoading.observe(this, aBoolean -> {

            if (aBoolean) {
                showLoading();
                getViewDataBinding().srReloadData.setRefreshing(true);
            } else {
                hideLoading();
                getViewDataBinding().srReloadData.setRefreshing(false);
            }

        });

        mViewModel.noInternet.observe(this, aVoid -> {
            showErrorDialog();
        });

        mViewModel.carsListLiveData.observe(this, carList -> {
            if (page == 1)
                mAdapter.setData(carList);
            else {
                mAdapter.addData(carList);
            }
            if (carList.size() % 10 != 0) {
                this.lastPage = true;
            }
            page++;
        });

        mViewModel.lastPage.observe(this, lastPage -> {
            if (lastPage) {
                this.lastPage = true;
            } else {
                page = 1;
                this.lastPage = false;
            }
        });


    }


    private void initRecyclerView() {
        getViewDataBinding().rvCarsList.setHasFixedSize(true);
        getViewDataBinding().rvCarsList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CarsAdapter();
        getViewDataBinding().rvCarsList.setAdapter(mAdapter);
        onScrollListener = new OnScrollListener((LinearLayoutManager) getViewDataBinding().rvCarsList.getLayoutManager(), this);
        getViewDataBinding().rvCarsList.addOnScrollListener(onScrollListener);

    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cars;
    }

    @Override
    public CarsListViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void onLoadMore() {
        if (!lastPage)
            mViewModel.loadMoreData(page);
    }

    @Override
    protected void onDestroy() {
        onScrollListener.onLoadMoreListener = null;
        super.onDestroy();
    }
}
