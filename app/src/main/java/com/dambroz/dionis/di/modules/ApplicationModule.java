package com.dambroz.dionis.di.modules;

import android.app.Application;
import android.content.Context;

import com.dambroz.dionis.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application app;

    public ApplicationModule(Application app) {
        this.app = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return app;
    }

    @Provides
    Application provideApplication() {
        return app;
    }
}
