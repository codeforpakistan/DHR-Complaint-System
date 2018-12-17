package com.dohr.complaint.cell.javaClasses;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.dohr.complaint.cell.R;


public class FullScreenVideo extends AppCompatActivity {
    VideoView video_preview;
    String vidUri;
    private MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen_video);
        Intent intent = getIntent();
        vidUri = intent.getStringExtra("vidUri");
        video_preview = findViewById(R.id.video_preview);
        if (mediaController == null) {
            mediaController = new MediaController(this);
            // Set the videoView that acts as the anchor for the MediaController.
            mediaController.setAnchorView(video_preview);
            // Set MediaController for VideoView
            video_preview.setMediaController(mediaController);
            video_preview.setVideoURI(Uri.parse(vidUri));
            video_preview.start();
        }


    }
}
