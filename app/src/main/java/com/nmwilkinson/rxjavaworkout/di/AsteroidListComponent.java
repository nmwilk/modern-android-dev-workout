package com.nmwilkinson.rxjavaworkout.di;

import com.nmwilkinson.rxjavaworkout.activity.AsteroidListActivity;
import com.nmwilkinson.rxjavaworkout.activity.DatesActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AsteroidsApplicationComponent.class, modules = {ActivityModule.class, AsteroidListModule.class})
public interface AsteroidListComponent
{
    void inject(AsteroidListActivity asteroidListActivity);
}
