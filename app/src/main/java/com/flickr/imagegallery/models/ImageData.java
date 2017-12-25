package com.flickr.imagegallery.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by YounasBangash on 12/26/2017.
 */

public class ImageData {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("media")
    @Expose
    public Media media;
    @SerializedName("date_taken")
    @Expose
    public String dateTaken;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("published")
    @Expose
    public String published;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("author_id")
    @Expose
    public String authorId;
    @SerializedName("tags")
    @Expose
    public String tags;
}
