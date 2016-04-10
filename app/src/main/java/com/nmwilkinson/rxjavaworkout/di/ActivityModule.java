package com.nmwilkinson.rxjavaworkout.di;

import android.app.Activity;
import android.view.View;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule
{
    private final Activity activity;
    private final View.OnClickListener clickListener;

    public ActivityModule(final Activity activity, final View.OnClickListener clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides @PerActivity
    Activity activity() {
        return activity;
    }

    @Provides @PerActivity
    View.OnClickListener clickListener() {
        return clickListener;
    }
}
