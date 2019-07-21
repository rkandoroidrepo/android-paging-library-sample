package com.paging.com.mysample.imagelist;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.paging.com.mysample.R;
import com.paging.com.mysample.pojo.Image;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by ramkrishna.kushwah on 21/07/2019
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolder> {


    private Context mCtx;
    private List<Image> imageList;
    private ItemClickListener listener;


    public ImageAdapter(Context mCtx, List<Image> imageList, ItemClickListener listener) {
        this.mCtx = mCtx;
        this.imageList = imageList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.image_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final Image image = imageList.get(position);
        holder.setData(image, listener);
//        holder.userName.setText(image.getUser());
//        holder.like.setText(image.getLikes().toString());
//        holder.comments.setText(image.getComments().toString());
//
//        Picasso.get()
//                .load(image.getLargeImageURL())
//                .placeholder(R.drawable.shimmer_background)
//                .into(holder.image);
//
//        Transformation transformation = new RoundedTransformationBuilder()
//                .borderColor(Color.BLACK)
//                .borderWidthDp(1)
//                .cornerRadiusDp(30)
//                .oval(false)
//                .build();
//
//        if (!image.getUserImageURL().isEmpty()) {
//            Picasso.get()
//                    .load(image.getUserImageURL())
//                    .fit()
//                    .transform(transformation)
//                    .into(holder.userImage);
//        }
//
//        holder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick("");
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    interface ItemClickListener {
        void onItemClick(String imageURL);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView userName, like, comments;
        ImageView image, userImage;

        public ItemViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);
            userImage = view.findViewById(R.id.user_image);
            userName = view.findViewById(R.id.user);
            like = view.findViewById(R.id.like);
            comments = view.findViewById(R.id.comments);
        }

        public void setData(final Image image, final ItemClickListener listener) {
            userName.setText(image.getUser());
            like.setText(image.getLikes().toString());
            comments.setText(image.getComments().toString());

            Picasso.get()
                    .load(image.getLargeImageURL())
                    .placeholder(R.drawable.shimmer_background)
                    .into(this.image);

            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.BLACK)
                    .borderWidthDp(1)
                    .cornerRadiusDp(30)
                    .oval(false)
                    .build();

            if (!image.getUserImageURL().isEmpty()) {
                Picasso.get()
                        .load(image.getUserImageURL())
                        .fit()
                        .transform(transformation)
                        .into(userImage);
            }

            this.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(image.getLargeImageURL());
                }
            });

        }
    }

}
