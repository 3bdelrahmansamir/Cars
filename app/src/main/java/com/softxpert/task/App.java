package com.softxpert.task;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class App extends MultiDexApplication {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mContext = this;

    }

    public static Context getContext() {
        return mContext;
    }

}
