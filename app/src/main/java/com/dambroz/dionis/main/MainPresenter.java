package com.dambroz.dionis.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.dambroz.dionis.app.MainApp;
import com.dambroz.dionis.retrofit.ServiceApi;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    @Inject
    ServiceApi serviceApi;

    public MainPresenter() {
        MainApp.getAppComponent().injectMainPresenter(this);
    }
}
