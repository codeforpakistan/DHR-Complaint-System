package com.dohr.complaint.cell.UtilsClasses;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.iceteck.silicompressorr.SiliCompressor;

import java.net.URISyntaxException;


 class VideoCompressAsyncTask extends AsyncTask<String, String, String> {

    Context mContext;
    String compressedVideoPath;

    public VideoCompressAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("preExecute", "Compressing....");
    }

    @Override
    protected String doInBackground(String... paths) {
        try {
                        compressedVideoPath = SiliCompressor.with(mContext).compressVideo(paths[0], paths[1]);
                        Log.e( "compressedVideoPath: ", compressedVideoPath);
                    } catch (URISyntaxException e) {
                        Log.e("URISyntaxException", e.toString() );
                    }




        return compressedVideoPath;

    }


    @Override
    protected void onPostExecute(String compressedFilePath) {
        super.onPostExecute(compressedFilePath);
        Log.e("postExecute", "Compressed Successflly! ");
    }
}
