package com.paging.com.mysample.imagelist;

import com.paging.com.mysample.Util.NetworkStatus;
import com.paging.com.mysample.data.ImageRepository;
import com.paging.com.mysample.data.LoadDataCallBack;
import com.paging.com.mysample.di.ActivityScoped;
import com.paging.com.mysample.pojo.Image;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
@ActivityScoped
public class ImageListPresenter implements ImageListContract.Presenter {

    private static final String NETWORK_PROBLEM_PLEASE_TRY_AGAIN = "Network problem, Please try again...";
    private ImageRepository imageRepository;
    private ImageListContract.View view;

    @Inject
    public ImageListPresenter(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void loadImages(NetworkStatus networkStatus) {
        view.showLoadingIndicator(true);
        imageRepository.loadImage(networkStatus, 1, new LoadDataCallBack<List<Image>>() {
            @Override
            public void onSuccess(List<Image> images) {

                view.showLoadingIndicator(false);
                view.displayImages(images);
            }

            @Override
            public void onError(int errorCode) {
                view.showLoadingIndicator(false);
                view.showError(NETWORK_PROBLEM_PLEASE_TRY_AGAIN);
            }
        });
    }

    @Override
    public void loadMoreImage(NetworkStatus networkStatus) {
        view.showBottomLoadingIndicator(true);
        imageRepository.loadImage(networkStatus, 2, new LoadDataCallBack<List<Image>>() {
            @Override
            public void onSuccess(List<Image> images) {
                view.showBottomLoadingIndicator(false);
                view.displayImages(images);
            }

            @Override
            public void onError(int errorCode) {
                view.showBottomLoadingIndicator(false);
                view.showError(NETWORK_PROBLEM_PLEASE_TRY_AGAIN);
            }
        });
    }

    @Override
    public void takeView(ImageListContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }
}
