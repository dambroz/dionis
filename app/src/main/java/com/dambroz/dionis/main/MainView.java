package com.dambroz.dionis.main;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface MainView extends MvpView {

    void fillRecyclerView(List data);
}
