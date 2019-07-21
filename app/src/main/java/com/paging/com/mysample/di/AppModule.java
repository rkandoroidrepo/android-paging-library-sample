package com.paging.com.mysample.di;

import com.paging.com.mysample.data.ImageDataSource;
import com.paging.com.mysample.data.ImageRepository;
import com.paging.com.mysample.network.ImageServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static ImageDataSource provideImageRepository(ImageRepository imageRepository) {
        return imageRepository;
    }

    @Provides
    static ImageServices provideImageService(Retrofit retrofit) {
        return retrofit.create(ImageServices.class);
    }
}
