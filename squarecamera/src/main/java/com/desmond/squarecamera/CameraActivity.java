package com.desmond.squarecamera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class CameraActivity extends AppCompatActivity {

    public static final String TAG = CameraActivity.class.getSimpleName();

    public static final String IMAGE_URI = "image_uri";
    public static final String SAVE_TO_GALLERY = "save_to_gallery";

    Uri uri;
    boolean saveToGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.squarecamera__CameraFullScreenTheme);
        super.onCreate(savedInstanceState);



        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.squarecamera__activity_camera);

        if (getIntent().getExtras().containsKey(IMAGE_URI)){

            uri=getIntent().getExtras().getParcelable(IMAGE_URI);
        }
        if (getIntent().getExtras().containsKey(SAVE_TO_GALLERY)){

            saveToGallery=getIntent().getExtras().getBoolean(SAVE_TO_GALLERY);
        }else{
            saveToGallery=false;
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, CameraFragment.newInstance(uri), CameraFragment.TAG)
                    .commit();
        }
    }

    public void returnPhotoUri(Uri uri) {
        Intent data = new Intent();
        data.setData(uri);

        if (getParent() == null) {
            setResult(RESULT_OK, data);
        } else {
            getParent().setResult(RESULT_OK, data);
        }

        finish();
    }

    public void onCancel(View view) {
        getSupportFragmentManager().popBackStack();
    }
}
