package com.anykeyapp;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.anykeyapp.di.AppComponent;
import com.anykeyapp.di.DaggerAppComponent;
import com.anykeyapp.di.modules.ApplicationModule;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.lang.ref.WeakReference;

public class BinApplication extends Application {

    private static final String TAG = BinApplication.class.getSimpleName();

    private static WeakReference<Context> mContext;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        Log.d(TAG, "Starting");

        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mContext = new WeakReference<>(this);

        if (BuildConfig.DEBUG)
            FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true)
                .build());

    }

    public static Context getContext() {
        return mContext.get();
    }

    public synchronized static AppComponent getAppComponent() {
        return appComponent;
    }
}