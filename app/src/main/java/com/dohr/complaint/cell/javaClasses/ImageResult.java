package com.dohr.complaint.cell.javaClasses;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.dohr.complaint.cell.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageResult extends AppCompatActivity{
    @BindView(R.id.imagetest)
    ImageView captureImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_result);

        ButterKnife.bind(this);

       Intent intent =getIntent();
       String us= intent.getStringExtra("uri");
        Uri myUri = Uri.parse(us);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), myUri);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bytes);
            captureImage.setImageBitmap(bitmap);
            int imageSize = sizeOf(bitmap);
            Log.e("imageSize", ""+ imageSize );

        } catch (IOException e) {
            Log.e("IOException", e.toString() );
        }

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    protected int sizeOf(Bitmap data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            return data.getRowBytes() * data.getHeight();
        } else {
            return data.getByteCount();
        }
    }
}
