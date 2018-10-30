package com.example.macchhindra.realmdatabase;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("tasky.realm")
                .schemaVersion(0).build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
