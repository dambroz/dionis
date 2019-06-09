package com.dambroz.dionis.main;

import com.arellomobile.mvp.MvpView;
import com.dambroz.dionis.main.model.Item;
import com.dambroz.dionis.main.model.VKAnswer;

import java.util.List;

public interface MainView extends MvpView {

    void fillRecyclerView(VKAnswer vkAnswer);

    void paginateRecyclerView(List<Item> data);

    void onError(String message);
}
