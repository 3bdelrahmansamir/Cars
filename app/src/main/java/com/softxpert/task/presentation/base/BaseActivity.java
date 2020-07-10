package com.softxpert.task.presentation.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
        extends AppCompatActivity {

    private T mViewDataBinding;
    private V mViewModel;
    ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("الرجاء الانتظار");
        loadingDialog.setCancelable(false);
    }

    public abstract int getBindingVariable();

    @LayoutRes
    public abstract int getLayoutId();

    public abstract V getViewModel();

    public void hideLoading() {
        loadingDialog.hide();
    }

    public void showLoading() {
        loadingDialog.show();
    }


    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }


    public void showErrorDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setMessage("حدث خطاء يرجى المحاوله مره اخرى")
                .setPositiveButton("موافق", (dialog, which) -> {

                }).show();
    }

}