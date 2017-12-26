package com.flickr.imagegallery.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by YounasBangash on 12/26/2017.
 */

public class Media {
    @SerializedName("m")
    @Expose
    private String m;

    public String getM() {
        return m;
    }
}
