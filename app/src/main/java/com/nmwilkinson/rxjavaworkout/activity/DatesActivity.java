package com.nmwilkinson.rxjavaworkout.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nmwilkinson.rxjavaworkout.AsteroidsApplication;
import com.nmwilkinson.rxjavaworkout.R;
import com.nmwilkinson.rxjavaworkout.di.ActivityModule;
import com.nmwilkinson.rxjavaworkout.di.DaggerDatesComponent;
import com.nmwilkinson.rxjavaworkout.di.DatesComponent;
import com.nmwilkinson.rxjavaworkout.model.Asteroids;
import com.nmwilkinson.rxjavaworkout.model.DatesAdapter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class DatesActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = DatesActivity.class.getSimpleName();

    @Inject
    DatesAdapter adapter;

    @Inject
    Observable<Asteroids> asteroidsObservable;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DatesComponent component = DaggerDatesComponent.builder()
                .asteroidsApplicationComponent(((AsteroidsApplication) getApplication()).component())
                .activityModule(new ActivityModule(this, this))
                .build();
        component.inject(this);

        setContentView(R.layout.dates_main);

        final RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setHasFixedSize(true);
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        subscription = asteroidsObservable
                .doOnNext(asteroids -> logAsteroids(asteroids))
                .flatMap(asteroids -> Observable.from(asteroids.dates()))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        adapter.sort();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(final Throwable error) {
                        Log.e(TAG, error.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(final String date) {
                        adapter.addDate(date);
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();

        subscription.unsubscribe();
    }

    @Override
    public void onClick(final View v) {
        final int index = Math.max(0, Math.min(adapter.getItemCount(), v.getId()));
        startActivity(AsteroidListActivity.createLaunchIntent(this, adapter.getDate(index)));
    }

    private void logAsteroids(final Asteroids asteroids) {
        final String format = String.format("Got %d asteroids, got %d dates", asteroids.getElementCount(), asteroids.getNearEarthObjects().size());
        Log.d(TAG, format);
        Toast.makeText(DatesActivity.this, format, Toast.LENGTH_SHORT).show();
    }
}
