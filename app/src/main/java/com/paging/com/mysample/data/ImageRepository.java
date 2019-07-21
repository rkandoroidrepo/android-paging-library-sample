package com.paging.com.mysample.data;

import com.paging.com.mysample.Util.ErrorCode;
import com.paging.com.mysample.Util.NetworkStatus;
import com.paging.com.mysample.pojo.Image;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
@Singleton
public class ImageRepository implements ImageDataSource {

    private ImageRemoteDataSource imageRemoteDataSource;
    private List<Image> imageList = new ArrayList<>();
    private int currentPageNumber = 1;
    private boolean isLoading;

    @Inject
    public ImageRepository(ImageRemoteDataSource imageRemoteDataSource) {
        this.imageRemoteDataSource = imageRemoteDataSource;
    }

    @Override
    public void loadImage(NetworkStatus networkStatus, int pageNumber,
                          final LoadDataCallBack<List<Image>> loadDataCallBack) {
        if (!isLoading) {
            if (networkStatus.isOnline()) {
                imageRemoteDataSource.loadImage(networkStatus, currentPageNumber,
                        new LoadDataCallBack<List<Image>>() {
                            @Override
                            public void onSuccess(List<Image> images) {
                                if (!images.isEmpty()) {
                                    ++currentPageNumber;
                                }
                                imageList.addAll(images);
                                loadDataCallBack.onSuccess(imageList);
                                isLoading = false;

                            }

                            @Override
                            public void onError(int errorCode) {
                                isLoading = false;
                                loadDataCallBack.onError(errorCode);
                            }
                        });
            } else {
                loadDataCallBack.onError(ErrorCode.NETWORK_ERROR);
            }
        }
    }

    @Override
    public void searchImage(NetworkStatus networkStatus,
                            final LoadDataCallBack<List<Image>> loadDataCallBack, String query) {
        if (networkStatus.isOnline()) {
            imageRemoteDataSource.loadImage(networkStatus, 1,
                    new LoadDataCallBack<List<Image>>() {
                @Override
                public void onSuccess(List<Image> images) {
                    loadDataCallBack.onSuccess(images);
                }

                @Override
                public void onError(int errorCode) {
                    loadDataCallBack.onError(errorCode);
                }
            });
        } else {
            loadDataCallBack.onError(ErrorCode.NETWORK_ERROR);
        }
    }
}
