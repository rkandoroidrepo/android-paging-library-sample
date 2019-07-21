package com.paging.com.mysample.data;

import com.paging.com.mysample.Util.NetworkStatus;
import com.paging.com.mysample.network.ImageServices;
import com.paging.com.mysample.pojo.IMResponse;
import com.paging.com.mysample.pojo.Image;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ramkrishna.kushwaha on 12/07/2019
 */
@Singleton
public class ImageRemoteDataSource implements ImageDataSource {

    private final static String API_KEY = "8439361-5e1e53a0e1b58baa26ab398f7";
    @Inject
    ImageServices imageServices;

    @Inject
    public ImageRemoteDataSource() {
        // Empty constructor
    }

    @Override
    public void loadImage(NetworkStatus networkStatus, int pageNumber,
                          final LoadDataCallBack<List<Image>> loadDataCallBack) {
        imageServices.getAllImages(API_KEY, pageNumber, "5")
                .enqueue(new Callback<IMResponse>() {
                    @Override
                    public void onResponse(Call<IMResponse> call, Response<IMResponse> response) {
                        if (response.isSuccessful()) {
                            loadDataCallBack.onSuccess(response.body().getImages());
                        }
                    }

                    @Override
                    public void onFailure(Call<IMResponse> call, Throwable t) {
                        loadDataCallBack.onError(100);
                    }
                });
    }

    @Override
    public void searchImage(NetworkStatus networkStatus,
                            LoadDataCallBack<List<Image>> loadDataCallBack,
                            String query) {
        // Not required
    }
}
