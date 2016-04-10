package com.nmwilkinson.rxjavaworkout.di;

import com.nmwilkinson.rxjavaworkout.activity.AsteroidListActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AsteroidsApplicationComponent.class, modules = {ActivityModule.class, AsteroidListModule.class})
public interface AsteroidDetailsComponent
{
    void inject(AsteroidListActivity detailsActivity);
}
