package com.paging.com.mysample;

import com.paging.com.mysample.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
public class MyApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

}
