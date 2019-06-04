package com.dambroz.dionis.app;

import android.app.Application;

import com.dambroz.dionis.di.components.AppComponent;
import com.dambroz.dionis.di.components.DaggerAppComponent;
import com.dambroz.dionis.di.modules.ApplicationModule;

public class MainApp extends Application {

    static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().applicationModule(new ApplicationModule(this)).build();
        appComponent.injectApp(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
