package com.dambroz.dionis.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dambroz.dionis.R;
import com.dambroz.dionis.main.adapters.ImageAdapter;
import com.dambroz.dionis.main.model.VKAnswer;
import com.google.gson.Gson;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.auth.VKAuthCallback;
import com.vk.api.sdk.auth.VKScope;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.requests.VKRequest;


import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (!VK.isLoggedIn())
            VK.login(this, Collections.singleton(VKScope.PHOTOS));

    }

    @Override
    public void fillRecyclerView(List data) {
        ImageAdapter imageAdapter = new ImageAdapter();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        VK.onActivityResult(requestCode, resultCode, data, new VKAuthCallback() {
            @Override
            public void onLogin(@NotNull VKAccessToken vkAccessToken) {
                VKRequest<JSONObject> request = new VKRequest<>("photos.get");
//                request.addParam("access_token", vkAccessToken.getAccessToken());
                request.addParam("owner_id", -1);
                request.addParam("album_id", "wall");
                request.addParam("count", "3");
                request.addParam("version", "5.95");

                VK.execute(request, new VKApiCallback<JSONObject>() {
                    @Override
                    public void success(JSONObject vkAnswer) {
                        String s  = vkAnswer.toString();
                        VKAnswer answer = new Gson().fromJson(s, VKAnswer.class);
                        Log.e("", s);
                    }

                    @Override
                    public void fail(@NotNull VKApiExecutionException e) {
                        Log.e("", "");
                    }
                });
            }

            @Override
            public void onLoginFailed(int i) {

            }
        });

    }
}
