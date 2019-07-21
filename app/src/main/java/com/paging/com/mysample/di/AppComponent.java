package com.paging.com.mysample.di;

import android.app.Application;

import com.paging.com.mysample.MyApplication;
import com.paging.com.mysample.data.ImageRepository;
import com.paging.com.mysample.data.ImageRepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
@Singleton
@Component(modules = {ImageRepositoryModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        AppModule.class})
public interface AppComponent extends AndroidInjector<MyApplication> {

    ImageRepository getImageRepository();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
