package com.xing.app.diarybook;

import android.util.Log;

import androidx.multidex.MultiDexApplication;

public class MyApplication extends MultiDexApplication {

    private final static String tag = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(tag,"onCreate()");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e(tag,"onLowMemory()");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e(tag,"onTrimMemory(int level) ->"+level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(tag,"onTerminate()");
    }
}
