package com.flickr.imagegallery.views;


/**
 * This
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.flickr.imagegallery.R;
import com.flickr.imagegallery.models.FlickerData;
import com.flickr.imagegallery.presenter.GalleryPresenter;
import com.flickr.imagegallery.utils.CheckInternetConnection;
import com.flickr.imagegallery.utils.RetrofitBuilder;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GalleryActivity extends AppCompatActivity {
    private RetrofitBuilder retrofitBuilder = null;
    private GalleryPresenter galleryPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallary_main);
        galleryPresenter = new GalleryPresenter(this);
        galleryPresenter.isNetworkConnected();


    }

    /**
     * this function receives the key from function presenter
     * @param key
     */
    public void receiveNetworkStatus(boolean key){
        Toast.makeText(this, ""
                +key, Toast.LENGTH_SHORT).show();
    }

    private void callRetrofit() {
        retrofitBuilder = new RetrofitBuilder(this);
        retrofitBuilder.getApi()
                .getAllPublicImages("json", "1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FlickerData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(FlickerData movies) {
                        Toast.makeText(GalleryActivity.this, "Amount of data is " +
                                movies.getItems().size(), Toast.LENGTH_SHORT).show();

                    }
                });
    }


}
