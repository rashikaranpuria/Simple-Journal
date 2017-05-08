package com.rashi.myjournal;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by rashi on 27/4/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //init Realm. Only done once
        Realm.init(this);

        //init Timber
        if (BuildConfig.DEBUG) {
            Timber.uprootAll();
            Timber.plant(new Timber.DebugTree());
        }
    }

}
