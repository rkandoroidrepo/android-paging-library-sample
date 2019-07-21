package com.paging.com.mysample.data;


import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
@Module
public abstract class ImageRepositoryModule {

    @Singleton
    @Binds
    abstract ImageDataSource provideImageRemoteDataSource(ImageRemoteDataSource dataSource);

}
