package com.paging.com.mysample;

import com.paging.com.mysample.Util.ErrorCode;
import com.paging.com.mysample.Util.NetworkStatus;
import com.paging.com.mysample.modal.ImageDataSource;
import com.paging.com.mysample.modal.ImageRemoteDataSource;
import com.paging.com.mysample.modal.ImageRepository;
import com.paging.com.mysample.pojo.IMResponse;
import com.paging.com.mysample.pojo.Image;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImageRepositoryTest {

    private ImageRepository repository;
    @Mock
    private NetworkStatus networkStatus;
    @Mock
    private ImageDataSource.LoadCallBackListener callBackListener;
    @Mock
    private ImageRemoteDataSource remoteDataSource;
    @Captor
    private ArgumentCaptor<ImageDataSource.LoadCallBackListener> listenerArgumentCaptor;
    @Captor
    private ArgumentCaptor<NetworkStatus> networkStatusArgumentCaptor;
    @Captor
    private ArgumentCaptor<String> queryCaptor;
    @Mock
    private IMResponse imResponse;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        repository = ImageRepository.getInstance(remoteDataSource);
    }

    @After
    public void destroy() {
        ImageRepository.destroyInstance();
    }

    @Test
    public void getImage() {
        List<Image> imageList = new ArrayList<>();
        IMResponse imResponse = new IMResponse();
        when(networkStatus.isOnline()).thenReturn(true);
        repository.getImages(networkStatus, callBackListener);
        verify(remoteDataSource).getImages(networkStatusArgumentCaptor.capture(), listenerArgumentCaptor.capture());
        listenerArgumentCaptor.getValue().onLoaded(imResponse);
        verify(callBackListener).onLoaded(imageList);
    }

    @Test
    public void getImageWhenNetworkError() {
        List<Image> imageList = new ArrayList<>();
        IMResponse imResponse = new IMResponse();
        when(networkStatus.isOnline()).thenReturn(false);
        repository.getImages(networkStatus, callBackListener);
        verify(remoteDataSource, times(0)).getImages(networkStatusArgumentCaptor.capture(), listenerArgumentCaptor.capture());
        verify(callBackListener).onError(ErrorCode.NETWORK_ERROR);
    }

    @Test
    public void searchImage() {
        List<Image> imageList = new ArrayList<>();
        imageList.add(new Image());
        imageList.add(new Image());
        when(imResponse.getImages()).thenReturn(imageList);
        when(networkStatus.isOnline()).thenReturn(true);
        repository.getImageSearch(networkStatus, "nature", callBackListener);
        verify(remoteDataSource).getImageSearch(networkStatusArgumentCaptor.capture(), queryCaptor.capture(), listenerArgumentCaptor.capture());
        listenerArgumentCaptor.getValue().onLoaded(imResponse);
        verify(callBackListener).onLoaded(imageList);

    }
}

