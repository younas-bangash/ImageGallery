package com.flickr.imagegallery.presenter;

import com.flickr.imagegallery.models.FlickerData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by YounasBangash on 12/26/2017.
 */

public interface RetrofitInterface {

    @GET("services/feeds/photos_public.gne?")
    Observable<FlickerData> getAllPublicImages(@Query("format") String format,
                                               @Query("nojsoncallback") String nojsoncallback);
}
