package com.nmwilkinson.rxjavaworkout;

import android.app.Application;

import com.nmwilkinson.rxjavaworkout.di.AsteroidsApplicationComponent;
import com.nmwilkinson.rxjavaworkout.di.AsteroidsApplicationModule;
import com.nmwilkinson.rxjavaworkout.di.DaggerAsteroidsApplicationComponent;
import com.nmwilkinson.rxjavaworkout.rest.NeoWsService;

import javax.inject.Inject;

/**
 * Created by neil on 09/04/16.
 */
public class AsteroidsApplication extends Application {
    private AsteroidsApplicationComponent asteroidsApplicationComponent;

    @Inject
    NeoWsService asteroidsService;

    @Override public void onCreate() {
        super.onCreate();
        asteroidsApplicationComponent = DaggerAsteroidsApplicationComponent.builder()
                .asteroidsApplicationModule(new AsteroidsApplicationModule(this))
                .build();
    }

    public AsteroidsApplicationComponent component() {
        return asteroidsApplicationComponent;
    }
}
