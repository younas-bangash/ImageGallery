package com.flickr.imagegallery.presenter;

import android.text.Html;
import android.text.Spanned;

import com.flickr.imagegallery.R;
import com.flickr.imagegallery.models.ImageData;
import com.flickr.imagegallery.views.ImageDetailsActivity;

/**
 * Presenter of the ImageDetails Activity
 */

public class ImageDetailsPresenter implements ImageDetailsInteraction{
    private ImageDetailsActivity imageDetailsActivity = null;
    private ImageData imageData = null;

    public ImageDetailsPresenter(ImageDetailsActivity imageDetailsActivity, ImageData imageData) {
        this.imageDetailsActivity = imageDetailsActivity;
        this.imageData = imageData;
    }

    @Override
    public void setImageTags() {
        if(imageData.getTags().equals("") || imageData.getTags() == null){
            imageDetailsActivity.setImageTags(imageDetailsActivity.getString(R.string.no_tag));
        }else{
            imageDetailsActivity.setImageTags(imageData.getTags());
        }
    }

    @Override
    public void setDescription() {
        Spanned htmlAsSpanned = Html.fromHtml(imageData.getDescription());
        imageDetailsActivity.setDescription(htmlAsSpanned);
    }

    @Override
    public void setTitle() {
        if(imageData.getTitle().equals("") || imageData.getTitle() ==null)
            imageDetailsActivity.setTitle(imageDetailsActivity.getString(R.string.no_title_provided));
        else
            imageDetailsActivity.setTitle(imageData.getTitle());
    }

    @Override
    public void setDateTaken() {
        imageDetailsActivity.setDateTaken(imageData.getDateTaken());
    }
}
