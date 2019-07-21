package com.paging.com.mysample.data;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
public interface LoadDataCallBack<T> {

    void onSuccess(T t);

    void onError(int errorCode);
}
