package com.flickr.imagegallery.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flickr.imagegallery.R;
import com.flickr.imagegallery.models.ImageData;
import com.flickr.imagegallery.presenter.OnGalleryItemInteraction;
import com.flickr.imagegallery.utils.CircleProgressDrawable;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ImageData} and makes a call to the
 * specified {@link }.
 */
public class GalleryRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ImageData> mValues;
    private final OnGalleryItemInteraction mListener;

    public GalleryRVAdapter(List<ImageData> items, OnGalleryItemInteraction listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder rHolder, int position) {
        final ViewHolder holder = (ViewHolder) rHolder;
        holder.mItem = mValues.get(position);
        holder.uploaderName.setText(holder.mItem.getAuthor());
        loadPoster(holder.mItem.getMedia().getM(),holder.imageView);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onGalleryItemClick(holder.mItem);
                }
            }
        });
    }

    private void loadPoster(String posterUrl, SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.getHierarchy().setProgressBarImage(new CircleProgressDrawable());
        simpleDraweeView.setImageURI(posterUrl);
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private TextView uploaderName;
        private ImageData mItem;
        private SimpleDraweeView imageView;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = view.findViewById(R.id.imageView);
            uploaderName = view.findViewById(R.id.uploaderName);
        }
    }
}
