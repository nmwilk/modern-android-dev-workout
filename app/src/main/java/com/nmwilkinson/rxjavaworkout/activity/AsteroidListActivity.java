package com.nmwilkinson.rxjavaworkout.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nmwilkinson.rxjavaworkout.AsteroidsApplication;
import com.nmwilkinson.rxjavaworkout.R;
import com.nmwilkinson.rxjavaworkout.di.ActivityModule;
import com.nmwilkinson.rxjavaworkout.di.AsteroidDetailsComponent;
import com.nmwilkinson.rxjavaworkout.di.DaggerAsteroidDetailsComponent;
import com.nmwilkinson.rxjavaworkout.model.Asteroid;
import com.nmwilkinson.rxjavaworkout.model.AsteroidListAdapter;
import com.nmwilkinson.rxjavaworkout.model.Asteroids;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class AsteroidListActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String PARAM_DATE = "date";

    @Inject
    AsteroidListAdapter adapter;

    @Inject
    Observable<Asteroids> asteroidsObservable;

    private String date;
    private Subscription subscription;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AsteroidDetailsComponent component = DaggerAsteroidDetailsComponent.builder()
                .asteroidsApplicationComponent(((AsteroidsApplication) getApplication()).component())
                .activityModule(new ActivityModule(this, this))
                .build();
        component.inject(this);

        date = getIntent().getStringExtra(PARAM_DATE);

        setContentView(R.layout.asteroid_list);

        final RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setHasFixedSize(true);
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        subscription = asteroidsObservable.map(asteroids -> asteroids.nearEarthObjects(date))
                .flatMap(asteroids -> Observable.from(asteroids))
                .subscribe(new Subscriber<Asteroid>() {
                    @Override
                    public void onCompleted() {
                        adapter.sort();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(final Throwable e) {

                    }

                    @Override
                    public void onNext(final Asteroid asteroid) {
                        adapter.addAsteroid(asteroid);
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();

        subscription.unsubscribe();
    }

    public static Intent createLaunchIntent(final Context context, final String date) {
        final Intent intent = new Intent(context, AsteroidListActivity.class);
        intent.putExtra(PARAM_DATE, date);
        return intent;
    }

    @Override
    public void onClick(final View v) {
        // TODO something fancy.
    }
}
