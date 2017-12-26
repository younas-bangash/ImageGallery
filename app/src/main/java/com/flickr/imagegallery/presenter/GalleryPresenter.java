package com.flickr.imagegallery.presenter;

import com.flickr.imagegallery.utils.CheckInternetConnection;
import com.flickr.imagegallery.views.GalleryActivity;

/**
 * This class calls an interface for data transfer between activity
 */

public class GalleryPresenter implements GalleryPresenterInteraction {

    private GalleryActivity galleryActivity = null;

    public GalleryPresenter(GalleryActivity galleryActivity){
        this.galleryActivity = galleryActivity;
    }


    public void isNetworkConnected(){
        CheckInternetConnection checkInternetConnection =
                new CheckInternetConnection(galleryActivity);
        this.isConnectedToNetwork(checkInternetConnection.isNetworkAvailable());
    }



    @Override
    public void isConnectedToNetwork(boolean key) {
        galleryActivity.receiveNetworkStatus(key);
    }
}
