package com.flickr.imagegallery.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.flickr.imagegallery.R;
import com.flickr.imagegallery.models.FlickerData;
import com.flickr.imagegallery.models.ImageData;
import com.flickr.imagegallery.presenter.GalleryPresenter;
import com.flickr.imagegallery.presenter.OnGalleryItemInteraction;

import java.util.ArrayList;
import java.util.List;


public class GalleryActivity extends AppCompatActivity implements OnGalleryItemInteraction {
    private GalleryPresenter galleryPresenter = null;
    private ProgressBar progressBar = null;
    private RecyclerView recyclerView = null;
    private GalleryRVAdapter galleryRVAdapter = null;
    private List<ImageData> imageDataList = new ArrayList<>();
    private GridLayoutManager gridLayoutManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallary_main);
        initViews();
        galleryPresenter = new GalleryPresenter(this);
        galleryRVAdapter = new GalleryRVAdapter(imageDataList,this);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(galleryRVAdapter);
        galleryPresenter.isConnectedToNetwork();
    }


    /**
     * This function will init the views
     */
    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.image_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    /**
     * this function receives the key from function presenter
     * @param key - network status value
     */
    public void showNetworkStatusInView(boolean key){
        if(key){
            galleryPresenter.getPublicImages();
        }else{
            Toast.makeText(this, "Internet Connection Required",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This function will hide the progress bar when data is loaded
     */

    public void hideProgressbar(){
        progressBar.setVisibility(View.GONE);
    }


    /**
     * This function will receive the result from the api
     * @param flickerData - data object from the api
     */
    public void sendResultToView(FlickerData flickerData){
        imageDataList.addAll(flickerData.getItems());
        galleryRVAdapter.notifyDataSetChanged();
    }

    /**
     * THis will call when user click on the gallery item
     * @param imageData - data of the item which is clicked
     */
    @Override
    public void onGalleryItemClick(ImageData imageData) {
        Intent intent = new Intent(this,ImageDetailsActivity.class);
        Bundle data = new Bundle();
        data.putParcelable("imageData",imageData);
        data.putString("imageLink",imageData.getMedia().getM());
        intent.putExtras(data);
        startActivity(intent);
    }
}
