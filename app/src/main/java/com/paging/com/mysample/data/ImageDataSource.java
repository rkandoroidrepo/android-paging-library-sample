package com.paging.com.mysample.data;

import com.paging.com.mysample.Util.NetworkStatus;
import com.paging.com.mysample.pojo.Image;

import java.util.List;

/**
 * Created by ramkrishna.kushwaha on 12/07/2019
 */
public interface ImageDataSource {

    /**
     * Gets the images
     *
     * @param networkStatus
     * @param pageNumber       requested page number
     * @param loadDataCallBack call back listener
     */
    void loadImage(NetworkStatus networkStatus, int pageNumber,
                   LoadDataCallBack<List<Image>> loadDataCallBack);

    /**
     * Gets images based on the search query
     *
     * @param networkStatus
     * @param loadDataCallBack call back listener
     * @param query            query params
     */
    void searchImage(NetworkStatus networkStatus,
                     LoadDataCallBack<List<Image>> loadDataCallBack, String query);
}
