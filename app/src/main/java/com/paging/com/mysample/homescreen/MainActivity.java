package com.paging.com.mysample.homescreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.paging.com.mysample.R;
import com.paging.com.mysample.pojo.Image;

public class MainActivity extends AppCompatActivity {

    private ShimmerFrameLayout shimmerViewContainer;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
        final RecyclerView recyclerView = findViewById(R.id.image_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        adapter = new ItemAdapter(this);
        showLoadingIndicator(true);
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Image>>() {
            @Override
            public void onChanged(@Nullable final PagedList<Image> items) {

                if (shimmerViewContainer.getVisibility() == View.VISIBLE) {
                    showLoadingIndicator(false);
                }
                recyclerView.setVisibility(View.VISIBLE);
                adapter.submitList(items);

            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showLoadingIndicator(boolean active) {
        if (active) {
            shimmerViewContainer.setVisibility(View.VISIBLE);
            shimmerViewContainer.startShimmerAnimation();
        } else {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    shimmerViewContainer.stopShimmerAnimation();
                    shimmerViewContainer.setVisibility(View.GONE);
                }
            }, 2000);

        }
    }

    public void setUpUI() {
        Toolbar toolbar = findViewById(R.id.tabanim_toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        shimmerViewContainer = findViewById(R.id.shimmer_view_container);
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        shimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

}
