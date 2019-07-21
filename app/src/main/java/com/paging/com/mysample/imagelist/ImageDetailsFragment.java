package com.paging.com.mysample.imagelist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.paging.com.mysample.R;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
public class ImageDetailsFragment extends Fragment {

    private static final String IMAGE_URL = "imageURL";

    private View rootView;

    @Inject
    public ImageDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_image_details, container, false);
        // bind view using butter knife
        String imgaeURL = getArguments().getString(IMAGE_URL);
        showImage(imgaeURL);
        return rootView;
    }

    private void showImage(String imageURL) {
        if (!imageURL.isEmpty()) {
            ImageView imageView = rootView.findViewById(R.id.image);
            Picasso.get()
                    .load(imageURL)
                    .placeholder(R.drawable.shimmer_background)
                    .into(imageView);
        }
    }
}
