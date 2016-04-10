package com.nmwilkinson.rxjavaworkout.di;

import android.app.Application;

import com.nmwilkinson.rxjavaworkout.AsteroidsApplication;
import com.nmwilkinson.rxjavaworkout.model.Asteroids;
import com.nmwilkinson.rxjavaworkout.rest.NeoWsService;

import javax.inject.Singleton;

import dagger.Component;
import rx.Observable;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = AsteroidsApplicationModule.class)
public interface AsteroidsApplicationComponent
{
    // Field injections of any dependencies of the DemoApplication
    void inject(AsteroidsApplication application);

    // Exported things that non-di setup code will need (e.g. an activity).
    Application application();

    Observable<Asteroids> asteroidsObservable();
}
