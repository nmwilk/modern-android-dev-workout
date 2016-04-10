package com.nmwilkinson.rxjavaworkout.di;

import android.view.View;

import com.nmwilkinson.rxjavaworkout.model.AsteroidListAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class AsteroidListModule
{
    @Provides
    @PerActivity
    AsteroidListAdapter asteroidListAdapter(final View.OnClickListener rowClickListener)
    {
        return new AsteroidListAdapter(rowClickListener);
    }
}
