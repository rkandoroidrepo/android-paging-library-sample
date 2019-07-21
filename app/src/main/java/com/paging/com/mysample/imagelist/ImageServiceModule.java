package com.paging.com.mysample.imagelist;

import com.paging.com.mysample.network.ImageServices;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
@Module
public class ImageServiceModule {

    @Provides
    static ImageServices provideImageService(Retrofit retrofit) {
        return retrofit.create(ImageServices.class);
    }

}
