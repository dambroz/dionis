package com.dambroz.dionis.di.components;

import android.app.Application;
import android.content.Context;

import com.dambroz.dionis.app.MainApp;
import com.dambroz.dionis.di.ApplicationContext;
import com.dambroz.dionis.di.ApplicationScope;
import com.dambroz.dionis.di.modules.ApplicationModule;
import com.dambroz.dionis.di.modules.RetrofitModule;
import com.dambroz.dionis.main.MainPresenter;
import com.dambroz.dionis.retrofit.ServiceApi;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, RetrofitModule.class})
public interface AppComponent {
    @ApplicationContext
    Context context();

    Application application();

    ServiceApi getServiceApi();

    void injectApp(MainApp app);

    void injectMainPresenter(MainPresenter presenter);
}
