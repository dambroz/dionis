package com.dambroz.dionis.main;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.dambroz.dionis.app.MainApp;
import com.dambroz.dionis.main.model.VKAnswer;
import com.dambroz.dionis.retrofit.ServiceApi;
import com.google.gson.Gson;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    @Inject
    ServiceApi serviceApi;

    public MainPresenter() {
        MainApp.getAppComponent().injectMainPresenter(this);
    }


    /**
     * Загрузка первичной информации
     */
    void loadPhoto() {
        VKRequest<JSONObject> request = new VKRequest<>("photos.get");
//                request.addParam("access_token", vkAccessToken.getAccessToken());
        request.addParam("owner_id", -1);
        request.addParam("album_id", "wall");
        request.addParam("count", "6");
        request.addParam("version", "5.95");

        VK.execute(request, new VKApiCallback<JSONObject>() {
            @Override
            public void success(JSONObject vkAnswer) {
                String s  = vkAnswer.toString();
                VKAnswer answer = new Gson().fromJson(s, VKAnswer.class);
                getViewState().fillRecyclerView(answer);
            }

            @Override
            public void fail(@NotNull VKApiExecutionException e) {
                getViewState().onError(e.getErrorMsg());
            }
        });
    }

    /**
     * Получить следующую порцию данных
     * @param page
     * @param totalItemsCount
     */
    void getNextData(int page, int totalItemsCount) {
        //VKRequest не может десериальзовать в нужный класс. Поэтому используем JSONObject,
        //который приведем к нужному
        VKRequest<JSONObject> request = new VKRequest<>("photos.get");
//                request.addParam("access_token", vkAccessToken.getAccessToken());
        request.addParam("owner_id", -1);
        request.addParam("album_id", "wall");
        request.addParam("count", "6");
        request.addParam("version", "5.95");

        VK.execute(request, new VKApiCallback<JSONObject>() {
            @Override
            public void success(JSONObject vkAnswer) {
                String s  = vkAnswer.toString();
                VKAnswer answer = new Gson().fromJson(s, VKAnswer.class);
                //Добавляем данные к списку
                getViewState().paginateRecyclerView(answer.getResponse().getItems());
            }

            @Override
            public void fail(@NotNull VKApiExecutionException e) {
                getViewState().onError(e.getErrorMsg());
            }
        });
    }
}
