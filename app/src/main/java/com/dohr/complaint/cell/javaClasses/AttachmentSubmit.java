package com.dohr.complaint.cell.javaClasses;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.afollestad.materialcamera.MaterialCamera;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.UtilsClasses.CheckNetworkConnection;
import com.dohr.complaint.cell.UtilsClasses.Config;
import com.dohr.complaint.cell.UtilsClasses.FileUtils;
import com.dohr.complaint.cell.UtilsClasses.NonScrollGridView;
import com.dohr.complaint.cell.UtilsClasses.UserPrefence;

import com.dohr.complaint.cell.interfaceClasses.ComplaintApi;
import com.dohr.complaint.cell.interfaceClasses.ImageAdapterListener;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.ImageAdapter;
import com.dohr.complaint.cell.modelClasses.RegisterComplaintModle;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iceteck.silicompressorr.SiliCompressor;
import com.iceteck.silicompressorr.videocompression.MediaController;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.UserPref;

//import com.github.tcking.giraffecompressor.GiraffeCompressor;

public class AttachmentSubmit extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "getclass" ;
    private static final String URL_REG= Config.BaseUrl;
    File camera_file;
    Uri camera_URI;
    ImageView submitbtn, capture_video_btn,backbtn,captureImage,back_shabtn;
    private static final int REQUEST_TAKE_PHOTO1=6000;
    private String mCurrentPhotoPath;
    String type, subtype,subject, details, name, mobileno, address, personemail, personaddress;
    ProgressDialog progressDialog;
    HashMap<String,String> map = new HashMap<>();
    HashMap<String,RequestBody> data_map = new HashMap<>();
    SharedPreferences EditTextSharedPreferences,editformSharePreferences,editformSharePreferencestwo;
    SharedPreferences.Editor loginEditor,ed_edEditor,ed_edEditortwo;
    SharedPreferences sharedpreferences;
    String user_ids, apiToken;
    private File compressedfile;
    int RESULT_LOAD_VIDEO= 4000;
    private final int CAMERA_RESULT_CODE_VIDEO = 100;
    private final int MY_PERMISSIONS_REQUEST_IMAGE = 0x1;
    private final int MY_PERMISSIONS_REQUEST_VIDEO = 0x2;
    List<Image> images;
    NonScrollGridView mGridView;
    LinearLayout image_layout, video_layout;
    VideoView video_preview;
    ImageAdapter mImageAdapter;
    LinearLayout complaint_submitlayout;
    List<Uri> imagelist = new ArrayList<>();
    private List<MultipartBody.Part> fileParts=new ArrayList<>();
    String compressedVideoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attachment_submit);
        complaint_submitlayout = findViewById(R.id.complaint_submitlayout);
        // intents
        Intent intentt = getIntent();
        type =  intentt.getStringExtra("ComplaintType");
        subtype = intentt.getStringExtra("subcomplaint");
        subject = intentt.getStringExtra("subject");
        details = intentt.getStringExtra("details");
        name = intentt.getStringExtra("name");
        mobileno = intentt.getStringExtra("mobileno");
        address = intentt.getStringExtra("location");
        personemail = intentt.getStringExtra("personemail");
        personaddress = intentt.getStringExtra("PersonAddress");
        // initialization
        progressDialog = new ProgressDialog(this);
        captureImage=findViewById(R.id.captureImage);
        back_shabtn=findViewById(R.id.back_shabtn);
        submitbtn = findViewById(R.id.submitbtn);
        backbtn = findViewById(R.id.backbtn);
        mGridView=findViewById(R.id.gridlayout);
        image_layout=findViewById(R.id.image_layout);
        video_layout=findViewById(R.id.video_layout);
        video_preview=findViewById(R.id.video_preview);
        capture_video_btn = findViewById(R.id.capture_video_btn);
        captureImage.setOnClickListener(this);
        capture_video_btn.setOnClickListener(this);
        submitbtn.setOnClickListener(this);
        back_shabtn.setOnClickListener(this);
        sharedpreferences = getSharedPreferences(UserPref,Context.MODE_PRIVATE);
        user_ids = sharedpreferences.getString(UserPrefence.User_id,"no id");
        apiToken = sharedpreferences.getString(UserPrefence.Api_token,"no data");
        }
/****************************************DialogBox on button click***************************/

    private void showDialogue() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog previewDilaog = new Dialog(AttachmentSubmit.this, R.style.dialog_theme);
                previewDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                previewDilaog.setCancelable(true);
                previewDilaog.setContentView(R.layout.dialoguebox);
                previewDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                Button agree = previewDilaog.findViewById(R.id.agree);
                agree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //submitform to server
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.setTitle("Please wait");
                                progressDialog.setMessage("Uploading data...");
                                progressDialog.setCanceledOnTouchOutside(false);
                                boolean isNetworkAvailable = CheckNetworkConnection.checkNetworkConnection(AttachmentSubmit.this);
                                if (isNetworkAvailable){
                                    progressDialog.show();
                                    dataUploadToServir();
                                    previewDilaog.dismiss();
                                }
                                else {
                                    progressDialog.dismiss();
                                    //no_complaint.setVisibility(View.VISIBLE);
                                    Snackbar snackbar = Snackbar.make(complaint_submitlayout, "No internet connection",Snackbar.LENGTH_LONG);

                                    View view = snackbar.getView();
                                    view.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                                    snackbar.show();
                                    previewDilaog.dismiss();
                                    //Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
                                }



                            }
                        });
                    }
                });

                Button editform = previewDilaog.findViewById(R.id.editform);
                editform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(AttachmentSubmit.this,NewComplaint.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        editformSharePreferences=getApplicationContext().getSharedPreferences("EditData",Context.MODE_PRIVATE);
                        editformSharePreferencestwo=getApplicationContext().getSharedPreferences("EditDatatwo",Context.MODE_PRIVATE);

                        ed_edEditor=editformSharePreferences.edit();
                        ed_edEditortwo=editformSharePreferencestwo.edit();
                        ed_edEditor.putString("ComplaintType", type);
                        ed_edEditor.putString("subcomplaint", subtype);
                        ed_edEditor.putString("subject",subject);
                        ed_edEditor.putString("details",details);

                        ed_edEditortwo.putString("name",name);
                        ed_edEditortwo.putString("mobileno",mobileno);
                        ed_edEditortwo.putString("location",address);
                        ed_edEditortwo.putString("PersonAddress",personaddress);
                        ed_edEditortwo.putString("personemail",personemail);

                        ed_edEditor.commit();
                        ed_edEditortwo.commit();
                    }
                });
                previewDilaog.show();
            }
        });

    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_IMAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    openImageDialog();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "please enable permission mannuallly", Toast.LENGTH_SHORT).show();

                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_VIDEO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    openVideoDialog();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "please enable permission mannuallly", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    /*************************************************************/
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.back_shabtn:
                startActivity(new Intent(AttachmentSubmit.this, ComplaintAgainst.class));
                break;
            case R.id.submitbtn:
                showDialogue();
                break;
            case R.id.captureImage:
                if (ActivityCompat.checkSelfPermission(AttachmentSubmit.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(AttachmentSubmit.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(AttachmentSubmit.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    openImageDialog();

                }else {
                    ActivityCompat.requestPermissions(AttachmentSubmit.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_IMAGE);
                }


                break;

            case R.id.capture_video_btn:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (ActivityCompat.checkSelfPermission(AttachmentSubmit.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(AttachmentSubmit.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(AttachmentSubmit.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            openVideoDialog();

                        }else {
                            ActivityCompat.requestPermissions(AttachmentSubmit.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                                    MY_PERMISSIONS_REQUEST_VIDEO);
                        }

                    }
                });
                break;
        }
    }
    private void openVideoDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog sheetDialog = new Dialog(AttachmentSubmit.this, R.style.dialog_theme);
                sheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                sheetDialog.setCancelable(true);
                sheetDialog.setContentView(R.layout.video_dialog);
                sheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                ImageView vid_capture = sheetDialog.findViewById(R.id.vid_capture);

                vid_capture.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        File saveDir = null;
                        // Only use external storage directory if permission is granted, otherwise cache directory is used by default
                        saveDir = new File(Environment.getExternalStorageDirectory(), "MaterialCamera");
                        saveDir.mkdirs();
                        @SuppressLint("ResourceType")
                        MaterialCamera materialCamera =
                                new MaterialCamera(AttachmentSubmit.this)
                                        .primaryColorAttr(R.color.colorGreen)
                                        .saveDir(saveDir)
                                        .showPortraitWarning(false)// Whether or not a warning is displayed if the user presses record in portrait orientation
                                        .allowRetry(true)
                                        .defaultToFrontFacing(true)
                                        .allowRetry(true)
                                        .forceCamera1()
                                        .maxAllowedFileSize(1024 * 1024 * 5)
                                        .defaultToFrontFacing(false)
                                        .qualityProfile(MaterialCamera.QUALITY_LOW)
                                        .autoSubmit(false)
                                        .labelConfirm(R.string.mcam_use_video);
                        materialCamera.start(CAMERA_RESULT_CODE_VIDEO);

                        sheetDialog.dismiss();
                    }
                });

                ImageView open_vidgallery = sheetDialog.findViewById(R.id.open_vidgallery);
                open_vidgallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(Intent.createChooser(i,"Select Video"), RESULT_LOAD_VIDEO);
                        sheetDialog.dismiss();
                    }
                });

                sheetDialog.show();
            }
        });

    }
    private void openImageDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "imagelist.size: "+imagelist.size() );
                if (imagelist.size() < 3){
                    final Dialog sheetDialog = new Dialog(AttachmentSubmit.this, R.style.dialog_theme);
                    sheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    sheetDialog.setCancelable(true);
                    sheetDialog.setContentView(R.layout.attachimage_dialog);
                    sheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                    ImageView take_photo = sheetDialog.findViewById(R.id.take_photo);
                    take_photo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // call implicit intent to launch camera
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            // Ensure that there's a camera activity to handle the intent
                            if (takePictureIntent.resolveActivity(getPackageManager()) != null){
                                //initi file variblae with null
                                camera_file = null;
                                try{
                                    camera_file = createImageFile();
                                    Log.e(TAG, "dispatchTakePictureIntent1: "+camera_file);
                                }catch (IOException e){
                                    Log.e(TAG, "IOException: "+e );
                                }
                                // Continue only if the File was successfully created
                                if (camera_file != null) {
                                    camera_URI = FileProvider.getUriForFile(AttachmentSubmit.this, "com.dohr.complaint.cell.fileprovider", camera_file);
                                    Log.e(TAG, "shah uri: "+camera_URI );
                                    // by this point we have the camera photo on disk
                                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, camera_URI);
                                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO1);

                                }
                            }
                            sheetDialog.dismiss();
                            // Toast.makeText(AttachmentSubmit.this, "Take Photo", Toast.LENGTH_SHORT).show();
                        }
                    });
                    ImageView open_gallery = sheetDialog.findViewById(R.id.open_gallery);
                    open_gallery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImagePicker.create(AttachmentSubmit.this)
                                    .returnMode(ReturnMode.NONE) // set whether pick and / or camera action should return immediate result or not.
                                    .folderMode(true) // folder mode (false by default)
                                    .toolbarFolderTitle("Select Folder") // folder selection title
                                    .toolbarImageTitle("Tap to select") // image selection title
                                    .toolbarArrowColor(Color.WHITE) // Toolbar 'up' arrow color
                                    .includeVideo(false) // Show vidicon on image picker
                                    .single() // single mode
                                    .multi() // multi mode (default mode)
                                    .limit(3 - imagelist.size()) // max images can be selected (99 by default)
                                    .showCamera(true) // show camera or not (true by default)
                                    .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                                    //.origin(images) // original selected images, used in multi mode
                                    // .exclude(images) // exclude anything that in image.getPath()
                                    // .excludeFiles(files) // same as exclude but using ArrayList<File>
                                    .theme(R.style.AppTheme) // must inherit ef_BaseTheme. please refer to sample
                                    .enableLog(false) // disabling log
                                    //.imageLoader(new GrayscaleImageLoder()) // custom image loader, must be serializeable
                                    .start(); // start image picker activity with request code

                            sheetDialog.dismiss();
                        }
                    });
                    sheetDialog.show();
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar snackbar = Snackbar
                                    .make(findViewById(R.id.card_container3), "You can upload max 3 images", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    });

                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        try {
            if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
                // Get a list of picked images from gallery
                image_layout.setVisibility(View.VISIBLE);
                images = ImagePicker.getImages(data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //final List<MultipartBody.Part> part1 = new ArrayList<>();
                        for (int i=0 ; i < images.size(); i++) {
                            Uri uri = getUriFromPath(images.get(i).getPath());
                            //part1.add(prepareFilePart("image[]", FileUtils.getFile(AttachmentSubmit.this, uri)));
                            fileParts.add(prepareFilePart("image[]", FileUtils.getFile(AttachmentSubmit.this, uri)));
                            imagelist.add(uri);
                            Log.e("fileParts", ""+fileParts);

                            /*for (int i1 = 0; i1 < imagelist.size(); i1++) {*/
                                mImageAdapter = new ImageAdapter(AttachmentSubmit.this, imagelist, new ImageAdapterListener() {
                                    @Override
                                    public void classOnClick(View v, int position) {
                                       /* for(int i=0; i<fileParts.size(); i++)
                                        {
                                            if(fileParts.get(i) == part1.get(i))
                                            {
                                                fileParts.remove(i);
                                                Log.e(TAG, "Removed: "+part1.get(i));
                                            }
                                        }*/
                                    }
                                });
                           /* }*/
                            mGridView.setAdapter(mImageAdapter);
                            mImageAdapter.addItems();
                            Log.e(TAG, "fileParts size: "+fileParts.size() );
                            }
                    }
                });

            }
             if (requestCode == REQUEST_TAKE_PHOTO1 && resultCode == RESULT_OK){
                if(camera_URI != null){
                   try {
                       compressedfile = new Compressor(this)
                               .setQuality(50)
                               .setCompressFormat(Bitmap.CompressFormat.JPEG)
                               .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath())
                               .compressToFile(camera_file);
                       delete(camera_file);
                       MultipartBody.Part part = prepareFilePart("image[]", compressedfile);
                       fileParts.add(part);
                       imagelist.add(FileUtils.getUri(compressedfile));
                       image_layout.setVisibility(View.VISIBLE);
                       mImageAdapter = new ImageAdapter(AttachmentSubmit.this, imagelist, new ImageAdapterListener() {
                           @Override
                           public void classOnClick(View v, int position) {
                               Toast.makeText(AttachmentSubmit.this, ""+position, Toast.LENGTH_SHORT).show();
                           }
                       });
                       mGridView.setAdapter(mImageAdapter);
                   } catch (IOException e) {
                       e.printStackTrace();
                       Log.e(TAG, "IOException: "+e.toString());
                   }

                }
            }
         if (requestCode == CAMERA_RESULT_CODE_VIDEO && resultCode == RESULT_OK){
                final File cameraVideoFile = new File(data.getData().getPath());
                MultipartBody.Part part1 = prepareFilePart("video", cameraVideoFile);
                fileParts.add(part1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        video_layout.setVisibility(View.VISIBLE);
                        Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(FileUtils.getPath(AttachmentSubmit.this, FileUtils.getUri(cameraVideoFile)),
                                MediaStore.Images.Thumbnails.FULL_SCREEN_KIND);
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(thumbnail);
                        video_preview.setBackgroundDrawable(bitmapDrawable);
                        //standalone player
                        ImageView playbtn = findViewById(R.id.playbtn);
                        playbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Uri video = FileUtils.getUri(cameraVideoFile);
                                Intent intent = new Intent(AttachmentSubmit.this, FullScreenVideo.class);
                                intent.putExtra("vidUri", String.valueOf(video));
                                Log.e("onClick: ", String.valueOf(video));
                                Log.e("uri: ", "" + video);
                                startActivity(intent);
                                        }
                                    });

                                }
                            });

            }

            if (requestCode == RESULT_LOAD_VIDEO && resultCode == RESULT_OK) {
                    final String selectedVideoPath = getPath(data.getData());
                    Log.e(TAG, "selectedVideoPath: "+selectedVideoPath );

                    if(selectedVideoPath == null) {
                        Toast.makeText(this, "Picking video from gallery failed", Toast.LENGTH_SHORT).show();
                        } else {
                            final File galleryVideoFile = FileUtils.getFile(AttachmentSubmit.this, data.getData());
                        // Get length of file in bytes
                        long fileSizeInBytes = galleryVideoFile.length();
                        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                        long fileSizeInKB = fileSizeInBytes / 1024;
                        //  Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                        long fileSizeInMB = fileSizeInKB / 1024;
                        Log.e(TAG, "fileSizeInMB: "+fileSizeInMB);
                        if (fileSizeInMB > 17){
                            Toast.makeText(this, "Video file must not be greater then 200 MB", Toast.LENGTH_LONG).show();
                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getPackageName() + "/media/videos");
                                    if (f.mkdirs() || f.isDirectory())
                                        //compress and output new video specs
                                        new VideoCompressAsyncTask(AttachmentSubmit.this).execute(data.getData().toString(), f.getPath());

                                   // FileUtils.getFile(AttachmentSubmit.this, getUriFromPath(compressedVideoPath)


                                    //standalone player
                                    ImageView playbtn = findViewById(R.id.playbtn);
                                    playbtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Uri video = FileUtils.getUri(galleryVideoFile);
                                            Intent intent = new Intent(AttachmentSubmit.this, FullScreenVideo.class);
                                            intent.putExtra("vidUri",String.valueOf(video));
                                            Log.e("onClick: ", String.valueOf(video));
                                            Log.e("uri: ", ""+video);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        }


                        }
            }

        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
            Log.e(TAG, "onActivityResult: "+e.toString() );
        }
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if(cursor!=null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        else return null;
    }
  /**********************deleting images from file*********************************/
    private void delete(File file) {
        file.delete();
        if(file.exists()){
            try {
                file.getCanonicalFile().delete();
            } catch (IOException e) {
                Log.e(TAG, "IOException: "+e.toString() );
            }
            if(file.exists()){
                getApplicationContext().deleteFile(file.getName());
            }
        }
    }
    private Uri getUriFromPath(String filePath) {
        long photoId;
        Uri photoUri = MediaStore.Images.Media.getContentUri("external");

        String[] projection = {MediaStore.Images.ImageColumns._ID};
        // TODO This will break if we have no matching item in the MediaStore.
        Cursor cursor = getContentResolver().query(photoUri, projection, MediaStore.Images.ImageColumns.DATA + " LIKE ?", new String[] { filePath }, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(projection[0]);
        photoId = cursor.getLong(columnIndex);

        cursor.close();
        return Uri.parse(photoUri.toString() + "/" + photoId);
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName =   timeStamp + "_";
        File storageDir1 = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir1      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();

        Log.e(TAG, "createImageFile: "+mCurrentPhotoPath);
        return image;
    }
    private File createVideoFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String VideoFileName =   timeStamp + "_";
        File storageDir1 = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                VideoFileName,  /* prefix */
                ".mp4",         /* suffix */
                storageDir1      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        String mCurrentVideoPath = image.getAbsolutePath();

        Log.e(TAG, "createVideoFile: "+mCurrentVideoPath);
        return image;
    }
    //helper methods
    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, File file) {
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(FileUtils.getMimeType(file)), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);

    }
    @NonNull
    private RequestBody createPartFromString(String val) {
        return RequestBody.create(okhttp3.MultipartBody.FORM,  val);
    }
    private void dataUploadToServir(){
        data_map.put("api_token", createPartFromString(apiToken));
        data_map.put("user_id", createPartFromString(user_ids));
        data_map.put("complaint_type", createPartFromString(type));
        data_map.put("sub_complaint_type", createPartFromString(subtype));
        data_map.put("subject", createPartFromString(subject));
        data_map.put("details",createPartFromString(details));
        data_map.put("dept_name",createPartFromString(name));
        data_map.put("person_phone_number",createPartFromString(mobileno));
        data_map.put("location",createPartFromString(address));
        data_map.put("person_email",createPartFromString(personemail));
        data_map.put("person_address",createPartFromString(personaddress));

        Log.e(TAG, "dataUploadToServir: "+data_map.toString() );
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_REG)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



        ComplaintApi rest = retrofit.create(ComplaintApi.class);

        Log.e(TAG, "data_map: "+data_map.toString() );
        Log.e(TAG, "fileParts: "+fileParts.toString() );
        Call<RegisterComplaintModle> call =  rest.uploadData(data_map, fileParts);

        call.enqueue(new Callback<RegisterComplaintModle>() {
            @Override
            public void onResponse(Call<RegisterComplaintModle> call, final Response<RegisterComplaintModle> response) {
                Log.e(TAG, "response: "+ response);
                if(response.isSuccessful())
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "sucess: "+ response.body().getSuccess() );
                            Log.e(TAG, "status: "+ response.body().getStatus() );
                            Log.e(TAG, "message: "+ response.body().getMessage() );
                            int status = response.body().getStatus();
                            String success = response.body().getSuccess();
                            if (status == 200 ){
                                if (success.equals("true")){
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();

                                            Log.e("sucess: ",response.body().getSuccess());
                                            final Dialog sheetDialog = new Dialog(AttachmentSubmit.this, R.style.dialog_theme);
                                            sheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            sheetDialog.setCancelable(false);
                                            sheetDialog.setContentView(R.layout.success_dialogue);
                                            sheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                                            Button dialog_text = sheetDialog.findViewById(R.id.dialog_text);
                                            dialog_text.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    sheetDialog.dismiss();
                                                    Intent homeIntent = new Intent(AttachmentSubmit.this,Home.class);
                                                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(homeIntent);
                                                    finish();
                                                }
                                            });
                                            sheetDialog.show();
                                        }
                                    });

                                }
                                else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                            Snackbar snackbar = Snackbar.make(complaint_submitlayout, "Server not responding. Please try again!",Snackbar.LENGTH_LONG);

                                            View view = snackbar.getView();
                                            view.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                                            snackbar.show();
                                            Toast.makeText(AttachmentSubmit.this, "not responding", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }

                            else  if (success.equals(false)){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        Snackbar snackbar = Snackbar.make(complaint_submitlayout, "Server not responding. Please try again!",Snackbar.LENGTH_LONG);

                                        View view = snackbar.getView();
                                        view.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                                        snackbar.show();
                                        Toast.makeText(AttachmentSubmit.this, "not responding", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }
                    });
                }
                else {
                    Log.e(TAG, "Response not successful");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<RegisterComplaintModle> call, final Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("Message", ""+t.getMessage());
                        progressDialog.dismiss();
                        Snackbar snackbar = Snackbar.make(complaint_submitlayout, "No internet connection",Snackbar.LENGTH_LONG);

                        View view = snackbar.getView();
                        view.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                        snackbar.show();
                    }
                });

            }
        });
    }

    class VideoCompressAsyncTask extends AsyncTask<String, String, String> {

        Context mContext;
        ProgressDialog mProgressDialog;

        public VideoCompressAsyncTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("preExecute", "Compressing....");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgressDialog = new ProgressDialog(AttachmentSubmit.this);
                    mProgressDialog.setTitle("Please Wait");
                    mProgressDialog.setMessage("Compressing Video Size...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();
                }
            });

        }

        @Override
        protected String doInBackground(final String... paths) {

                        try {
                            compressedVideoPath = SiliCompressor.with(mContext).compressVideo(paths[0], paths[1]);
                            Log.e( "compressedVideoPath: ", compressedVideoPath);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("Ex", e.toString());
                        }
                        return compressedVideoPath;

        }


        @Override
        protected void onPostExecute(String compressedFilePath) {
            super.onPostExecute(compressedFilePath);

            try {
                File compressFile = new File(compressedFilePath);
                Log.e(TAG, "compressFile: "+compressFile);
                MultipartBody.Part part1 = prepareFilePart("video", compressFile);
                fileParts.add(part1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("postExecute", "Compressed Successflly! ");
                        video_layout.setVisibility(View.VISIBLE);
                        Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(compressedVideoPath, MediaStore.Images.Thumbnails.FULL_SCREEN_KIND);
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(thumbnail);
                        video_preview.setBackgroundDrawable(bitmapDrawable);
                        mProgressDialog.dismiss();
                    }
                });

            }catch (Exception e){
                Log.e(TAG, "run: "+e.toString() );
            }

        }
    }


}







