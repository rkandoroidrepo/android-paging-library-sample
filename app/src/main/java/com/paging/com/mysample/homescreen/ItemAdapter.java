package com.paging.com.mysample.homescreen;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paging.com.mysample.R;
import com.paging.com.mysample.pojo.Image;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class ItemAdapter extends PagedListAdapter<Image, ItemAdapter.ItemViewHolder> {

    private static DiffUtil.ItemCallback<Image> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Image>() {
                @Override
                public boolean areItemsTheSame(Image oldItem, Image newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Image oldItem, Image newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private Context mCtx;

    public ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.image_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final Image image = getItem(position);
        holder.userName.setText(image.getUser().toString());
        holder.like.setText(image.getLikes().toString());
        holder.comments.setText(image.getComments().toString());

        Picasso.get()
                .load(image.getLargeImageURL())
                .placeholder(R.drawable.shimmer_background)
                .into(holder.image);

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
                    .into(holder.userImage);
        }
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
    }
}
