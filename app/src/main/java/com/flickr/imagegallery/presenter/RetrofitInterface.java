package com.flickr.imagegallery.presenter;

import com.flickr.imagegallery.models.FlickerData;
import com.flickr.imagegallery.models.ImageData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by YounasBangash on 12/26/2017.
 */

public interface RetrofitInterface {

    @GET("services/feeds/photos_public.gne?")//format=json&nojsoncallback=1")
    Observable<FlickerData> getAllPublicImages(@Query("format") String api_key,
                                               @Query("nojsoncallback") String language);
}
