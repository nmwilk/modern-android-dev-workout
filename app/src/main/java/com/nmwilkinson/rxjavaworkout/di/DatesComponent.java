package com.nmwilkinson.rxjavaworkout.di;

import com.nmwilkinson.rxjavaworkout.activity.DatesActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AsteroidsApplicationComponent.class, modules = {ActivityModule.class, DatesModule.class})
public interface DatesComponent
{
    void inject(DatesActivity datesActivity);
}
