package com.paging.com.mysample.imagelist;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.paging.com.mysample.R;
import com.paging.com.mysample.Util.AppNetworkStatus;
import com.paging.com.mysample.di.ActivityScoped;
import com.paging.com.mysample.pojo.Image;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
@ActivityScoped
public class ImageListFragment extends DaggerFragment implements ImageListContract.View,
        ImageAdapter.ItemClickListener {

    @BindView(R.id.no_network_layout)
    FrameLayout errorLayout;
    @BindView(R.id.image_list)
    RecyclerView imageRecyclerView;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerViewContainer;
    @BindView(R.id.bottom_progress_bar)
    LinearLayout bottomProgressBar;
    @Inject
    ImageListPresenter imageListPresenter;
    private View rootView;
    private Unbinder unbinder;
    private ImageAdapter imageAdapter;
    private boolean loading = true;
    private LinearLayoutManager layoutManager;
    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;
    private ImageListFragmentInteractionListener interactionListener;

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            visibleItemCount = imageRecyclerView.getChildCount();

            totalItemCount = layoutManager.getItemCount();
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

            int lastInScreen = firstVisibleItem + visibleItemCount;
            int getMoreProductsThreshold = totalItemCount;

            if (lastInScreen >= getMoreProductsThreshold && !loading && totalItemCount != 0) {
                imageListPresenter.loadMoreImage(new AppNetworkStatus(getActivity()));
            }
        }
    };

    @Inject
    public ImageListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            interactionListener = (ImageListFragmentInteractionListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_image_list, container, false);

        // bind view using butter knife
        unbinder = ButterKnife.bind(this, rootView);

        imageListPresenter.takeView(this);

        imageListPresenter.loadImages(new AppNetworkStatus(getActivity()));

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // unbind the view to free some memory
        imageListPresenter.dropView();
        unbinder.unbind();

    }

    @Override
    public void showLoadingIndicator(boolean active) {
        if (active) {
            shimmerViewContainer.setVisibility(View.VISIBLE);
            shimmerViewContainer.startShimmerAnimation();
        } else {
            shimmerViewContainer.stopShimmerAnimation();
            shimmerViewContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void showBottomLoadingIndicator(boolean active) {
        bottomProgressBar.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayImages(List<Image> imageList) {
        if (imageAdapter == null) {
            imageAdapter = new ImageAdapter(getActivity(), imageList, this);
            layoutManager = new LinearLayoutManager(getActivity());
            imageRecyclerView.setLayoutManager(layoutManager);
            imageRecyclerView.setHasFixedSize(true);
            imageRecyclerView.setAdapter(imageAdapter);
            imageRecyclerView.addOnScrollListener(scrollListener);
        } else {
            imageRecyclerView.invalidate();
            imageAdapter.notifyDataSetChanged();

        }
        loading = false;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(String imageURL) {
        if (interactionListener != null) {
            interactionListener.showImageDetailsFragment(imageURL);
        }
    }

    interface ImageListFragmentInteractionListener {
        void showImageDetailsFragment(String imageUrl);
    }

}
