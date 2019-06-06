package com.dambroz.dionis.app;

import android.app.Application;

import com.dambroz.dionis.di.components.AppComponent;
import com.dambroz.dionis.di.components.DaggerAppComponent;
import com.dambroz.dionis.di.modules.ApplicationModule;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKTokenExpiredHandler;

public class MainApp extends Application {

    static AppComponent appComponent;
    private VKTokenExpiredHandler tokenTracker = () -> {

    };

    @Override
    public void onCreate() {
        super.onCreate();
        VK.addTokenExpiredHandler(tokenTracker);
        appComponent = DaggerAppComponent.builder().applicationModule(new ApplicationModule(this)).build();
        appComponent.injectApp(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
