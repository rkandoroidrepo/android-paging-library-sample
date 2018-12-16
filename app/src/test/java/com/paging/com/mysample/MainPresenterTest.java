package com.paging.com.mysample;

import com.paging.com.mysample.Util.ErrorCode;
import com.paging.com.mysample.Util.NetworkStatus;
import com.paging.com.mysample.homescreen.MainActivity;
import com.paging.com.mysample.mvp.MainPresenter;
import com.paging.com.mysample.modal.ImageDataSource;
import com.paging.com.mysample.modal.ImageRepository;
import com.paging.com.mysample.pojo.Image;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {

    private MainPresenter presenter;
    @Mock
    private MainActivity view;
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private NetworkStatus networkStatus;
    @Mock
    private ImageDataSource.LoadCallBackListener callBackListener;
    @Captor
    private ArgumentCaptor<NetworkStatus> networkStatusArgumentCaptor;
    @Captor
    private ArgumentCaptor<ImageDataSource.LoadCallBackListener> callBackListenerArgumentCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(view, imageRepository);
    }

    @Test
    public void createPresenter() {
        presenter = new MainPresenter(view, imageRepository);
        verify(view).setPresenter(presenter);
    }

    @Test
    public void setUI() {
        presenter.start();
        verify(view).setUpUI();
    }

    @Test
    public void getImages() {
        when(networkStatus.isOnline()).thenReturn(true);
        List<Image> imageList = new ArrayList<>();
        presenter.getImages(networkStatus);
        verify(imageRepository, times(1)).getImages(networkStatusArgumentCaptor.capture(), callBackListenerArgumentCaptor.capture());
        callBackListenerArgumentCaptor.getValue().onLoaded(imageList);
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showLoadingIndicator(true);
        inOrder.verify(view).showLoadingIndicator(false);
        inOrder.verify(view).showImages(imageList);
    }

    @Test
    public void getImagesWhenNetworkNotAvailable() {
        when(networkStatus.isOnline()).thenReturn(false);
        presenter.getImages(networkStatus);
        verify(imageRepository, times(1)).getImages(networkStatusArgumentCaptor.capture(), callBackListenerArgumentCaptor.capture());
        callBackListenerArgumentCaptor.getValue().onError(ErrorCode.NETWORK_ERROR);
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showLoadingIndicator(true);
        inOrder.verify(view).showLoadingIndicator(false);
        inOrder.verify(view).showNetworkError(true);
    }

    @Test
    public void getImagesWhenNoResult() {
        when(networkStatus.isOnline()).thenReturn(true);
        presenter.getImages(networkStatus);
        verify(imageRepository, times(1)).getImages(networkStatusArgumentCaptor.capture(), callBackListenerArgumentCaptor.capture());
        callBackListenerArgumentCaptor.getValue().onError(ErrorCode.NO_RESULT);
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showLoadingIndicator(true);
        inOrder.verify(view).showLoadingIndicator(false);
        inOrder.verify(view).showError(presenter.filterError(ErrorCode.NO_RESULT));
    }

    @Test
    public void showFullImage(){
        String imageURL = "someURL";
        presenter.showFullImage(imageURL);
        verify(view).startFullImageActivity(imageURL);

    }

}
