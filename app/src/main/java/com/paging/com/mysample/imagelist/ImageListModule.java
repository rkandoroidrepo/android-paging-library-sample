package com.paging.com.mysample.imagelist;

import com.paging.com.mysample.di.ActivityScoped;
import com.paging.com.mysample.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
@Module
public abstract class ImageListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ImageListFragment imageListFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ImageDetailsFragment imageDetailsFragment();

    @ActivityScoped
    @Binds
    abstract ImageListContract.Presenter imageListPresenter(ImageListPresenter presenter);

}
