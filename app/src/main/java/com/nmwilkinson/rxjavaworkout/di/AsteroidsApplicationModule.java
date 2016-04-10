package com.nmwilkinson.rxjavaworkout.di;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nmwilkinson.rxjavaworkout.Consts;
import com.nmwilkinson.rxjavaworkout.model.Asteroids;
import com.nmwilkinson.rxjavaworkout.model.DateRange;
import com.nmwilkinson.rxjavaworkout.rest.NeoWsService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A module for Android-specific dependencies which require a {@link android.content.Context} or
 * {@link Application} to create.
 */
@Module
public class AsteroidsApplicationModule {
    public static final String BASE_URL = "https://api.nasa.gov/";

    private final Application application;

    public AsteroidsApplicationModule(final Application application) {
        this.application = application;
    }

    /**
     * Expose the application to the graph.
     */
    @Provides
    @Singleton
    Application application() {
        return application;
    }

    @Provides
    @Singleton
    OkHttpClient client() {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        clientBuilder.addInterceptor(interceptor);

        return clientBuilder.build();
    }

    @Provides
    @Singleton
    NeoWsService neoWsService(final OkHttpClient client) {
        final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        final Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return retrofit.create(NeoWsService.class);
    }

    @Provides @Singleton
    DateRange dateRange() {
        return DateRange.lastWeek();
    }

    @Provides
    @Singleton
    Observable<Asteroids> asteroidsObservable(final NeoWsService neoWsService, final DateRange dateRange) {
        return neoWsService
                .listAsteroids(dateRange.getStartDate(), dateRange.getEndDate(), Consts.NEOWS_API_KEY)
                .cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
