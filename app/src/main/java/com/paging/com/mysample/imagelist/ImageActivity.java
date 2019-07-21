package com.paging.com.mysample.imagelist;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.paging.com.mysample.R;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
public class ImageActivity extends DaggerAppCompatActivity implements ImageListFragment
        .ImageListFragmentInteractionListener {

    private static final String IMAGE_URL = "imageURL";
    @Inject
    Lazy<ImageListFragment> imageListFragmentProvider;
    @Inject
    Lazy<ImageDetailsFragment> imageDetailsFragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        setImageListFragment();
    }

    private void setImageListFragment() {
        ImageListFragment imageListFragment = imageListFragmentProvider.get();
        imageListFragment.setRetainInstance(true);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                imageListFragment).commit();
    }

    @Override
    public void showImageDetailsFragment(String imageUrl) {
        ImageDetailsFragment fragment = imageDetailsFragmentProvider.get();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_URL, imageUrl);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, imageDetailsFragmentProvider.get());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
