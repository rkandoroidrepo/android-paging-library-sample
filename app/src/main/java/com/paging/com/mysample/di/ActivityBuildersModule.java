package com.paging.com.mysample.di;

import com.paging.com.mysample.imagelist.ImageActivity;
import com.paging.com.mysample.imagelist.ImageListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
@Module
public abstract class ActivityBuildersModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ImageListModule.class})
    abstract ImageActivity contributeImageActivity();
}
