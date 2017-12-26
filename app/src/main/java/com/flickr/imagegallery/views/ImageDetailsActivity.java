package com.flickr.imagegallery.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flickr.imagegallery.R;
import com.flickr.imagegallery.models.ImageData;
import com.flickr.imagegallery.presenter.ImageDetailsPresenter;
import com.flickr.imagegallery.utils.CircleProgressDrawable;

public class ImageDetailsActivity extends AppCompatActivity {
    private ImageData imageData = null;
    private String imageLink = null;
    private SimpleDraweeView imageView = null;
    private TextView imageTitleValue, dateTakenValue, imageDescription, imageTagsValue;
    private ImageDetailsPresenter imageDetailsPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        initViews();
        Bundle data = getIntent().getExtras();
        if(data != null){
            imageData = data.getParcelable("imageData");
            imageLink = data.getString("imageLink");
        }
        imageDetailsPresenter = new ImageDetailsPresenter(this, imageData);
        loadPoster(imageLink,imageView);
        invokePresenter();
    }

    private void invokePresenter() {
        imageDetailsPresenter.setImageTags();
        imageDetailsPresenter.setTitle();
        imageDetailsPresenter.setDescription();
        imageDetailsPresenter.setDateTaken();
    }

    private void initViews() {
        imageView = findViewById(R.id.imageView);
        imageDescription = findViewById(R.id.imageDescription);
        dateTakenValue = findViewById(R.id.dateTakenValue);
        imageTitleValue = findViewById(R.id.imageTitleValue);
        imageTagsValue = findViewById(R.id.imageTagsValue);
        //noinspection ConstantConditions
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setDescription(Spanned text) {
        imageDescription.setText(text);
    }


    public void setTitle(String text) {
        imageTitleValue.setText(text);
    }

    public void setImageTags(String text){
        imageTagsValue.setText(text);
    }

    public void setDateTaken(String text) {
        dateTakenValue.setText(text);
    }

    private void loadPoster(String posterUrl, SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.getHierarchy().setProgressBarImage(new CircleProgressDrawable());
        simpleDraweeView.setImageURI(posterUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            onBackPressed();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.image_details_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
}
