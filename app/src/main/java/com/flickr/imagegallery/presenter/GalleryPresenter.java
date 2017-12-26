package com.flickr.imagegallery.presenter;

import com.flickr.imagegallery.models.FlickerData;
import com.flickr.imagegallery.utils.CheckInternetConnection;
import com.flickr.imagegallery.utils.RetrofitBuilder;
import com.flickr.imagegallery.views.GalleryActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * This class calls an interface for data transfer between activity
 */

public class GalleryPresenter implements GalleryPresenterInteraction {
    private GalleryActivity galleryActivity = null;
    public GalleryPresenter(GalleryActivity galleryActivity) {
        this.galleryActivity = galleryActivity;
    }


    @Override
    public void isConnectedToNetwork() {
        CheckInternetConnection checkInternetConnection =
                new CheckInternetConnection(galleryActivity);
        galleryActivity.showNetworkStatusInView(checkInternetConnection.isNetworkAvailable());
    }

    @Override
    public void getPublicImages() {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder(galleryActivity);
        retrofitBuilder.getApi().getAllPublicImages("json", "1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FlickerData>() {
                    @Override
                    public void onCompleted() {
                        galleryActivity.hideProgressbar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        galleryActivity.hideProgressbar();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(FlickerData flickerData) {
                        galleryActivity.sendResultToView(flickerData);
                    }
                });
    }
}
