package com.nmwilkinson.rxjavaworkout.di;

import android.view.View;

import com.nmwilkinson.rxjavaworkout.model.DatesAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class DatesModule
{
    @Provides
    @PerActivity
    DatesAdapter datesAdapter(final View.OnClickListener rowClickListener)
    {
        return new DatesAdapter(rowClickListener);
    }
}
