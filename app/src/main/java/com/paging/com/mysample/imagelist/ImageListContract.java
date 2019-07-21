package com.paging.com.mysample.imagelist;

import com.paging.com.mysample.Util.BasePresenter;
import com.paging.com.mysample.Util.BaseView;
import com.paging.com.mysample.Util.NetworkStatus;
import com.paging.com.mysample.pojo.Image;

import java.util.List;

/**
 * Created by ramkrishna.kushwaha on 12/07/2019
 */
public interface ImageListContract {

    interface View extends BaseView<Presenter> {

        /**
         * Show/hide loading indicator
         *
         * @param active flag
         */
        void showLoadingIndicator(boolean active);

        /**
         * Show/hide bottom loading indicator
         *
         * @param active flag
         */
        void showBottomLoadingIndicator(boolean active);

        /**
         * Bind images to Recycler view
         *
         * @param imageList
         */
        void displayImages(List<Image> imageList);

        /**
         * Display errors
         */
        void showError(String error);

    }

    interface Presenter extends BasePresenter<View> {

        /**
         * gets first set of images
         *
         * @param networkStatus
         */
        void loadImages(NetworkStatus networkStatus);

        /**
         * load more images while scrolling
         *
         * @param networkStatus
         */
        void loadMoreImage(NetworkStatus networkStatus);
    }
}
