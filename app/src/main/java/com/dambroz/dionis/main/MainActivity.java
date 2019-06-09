package com.dambroz.dionis.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dambroz.dionis.R;
import com.dambroz.dionis.main.adapters.ImageAdapter;
import com.dambroz.dionis.main.adapters.listeners.EndlessRecyclerViewScrollListener;
import com.dambroz.dionis.main.model.Item;
import com.dambroz.dionis.main.model.VKAnswer;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.auth.VKAuthCallback;
import com.vk.api.sdk.auth.VKScope;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String LIST_STATE = "listSTate";
    @InjectPresenter
    MainPresenter presenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    ImageAdapter imageAdapter;
    StaggeredGridLayoutManager layoutManager;
    /**
     * Текущее состояние списка. Необходимо при повороте экрана
     */
    private Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (!VK.isLoggedIn()) {
            VK.login(this, Collections.singleton(VKScope.PHOTOS));
            return;
        }

        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE);
            if (layoutManager == null) {
                layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            }
            layoutManager.onRestoreInstanceState(listState);
            return;
        }

        presenter.loadPhoto();
    }

    @Override
    public void fillRecyclerView(VKAnswer vkAnswer) {
        imageAdapter = new ImageAdapter(vkAnswer);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.getNextData(page, totalItemsCount);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);

    }

    @Override
    public void paginateRecyclerView(List<Item> data) {
        imageAdapter.items.addAll(data);
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        VK.onActivityResult(requestCode, resultCode, data, new VKAuthCallback() {
            @Override
            public void onLogin(@NotNull VKAccessToken vkAccessToken) {
                presenter.loadPhoto();
            }

            @Override
            public void onLoginFailed(int i) {
                onError("Не удалось авторизоваться");
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (layoutManager != null)
            listState = layoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE, listState);

    }
}
