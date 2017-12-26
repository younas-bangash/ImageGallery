package com.flickr.imagegallery.presenter;

import com.flickr.imagegallery.models.ImageData;

/**
 * Created by Developer on 12/26/2017.
 */

public interface OnGalleryItemInteraction {
    void onGalleryItemClick(ImageData imageData);
}
