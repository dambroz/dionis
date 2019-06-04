package com.dambroz.dionis.di.modules;

import com.dambroz.dionis.di.ApplicationScope;
import com.dambroz.dionis.retrofit.ServiceApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class RetrofitModule {
    @Provides
    @ApplicationScope
    ServiceApi provideServiceApi(Retrofit retrofit) {
        return retrofit.create(ServiceApi.class);
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl("").client(okHttpClient).build();
    }

    @Provides
    @ApplicationScope
    OkHttpClient getOkHttpCleint() {
        return new OkHttpClient.Builder()
                .build();
    }
}
