package com.nmwilkinson.rxjavaworkout.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nmwilkinson.rxjavaworkout.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by neil on 24/03/2016.
 */
public class AsteroidListAdapter extends RecyclerView.Adapter {
    private final List<Asteroid> asteroids = new ArrayList<>();

    private final View.OnClickListener rowClickListener;

    public AsteroidListAdapter(@NonNull final View.OnClickListener rowClickListener) {
        this.rowClickListener = rowClickListener;
    }

    public void addAsteroid(final Asteroid asteroid) {
        if (!this.asteroids.contains(asteroid)) {
            this.asteroids.add(asteroid);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int type) {
        // create a new view
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.asteroid_view, parent, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        v.setOnClickListener(rowClickListener);
        return new ViewHolder((FrameLayout) v);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int index) {
        final Asteroid asteroid = asteroids.get(index);
        final double missDistanceMKm = asteroid.getCloseApproachData().get(0).getMissDistance().getKilometers() / 1000000.0;
        ((ViewHolder) viewHolder).textView.setText(String.format("%s - %f MKm", asteroid.getName(), missDistanceMKm));
        viewHolder.itemView.setId(index);
    }

    @Override
    public int getItemCount() {
        return asteroids.size();
    }

    public void sort() {
        Collections.sort(asteroids, (lhs, rhs) ->
                new Double(lhs.getCloseApproachData().get(0).getMissDistance().getKilometers())
                        .compareTo(rhs.getCloseApproachData().get(0).getMissDistance().getKilometers()));
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        ViewHolder(final FrameLayout container) {
            super(container);
            textView = (TextView) container.findViewById(R.id.date);
        }
    }

}
