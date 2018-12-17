package com.dohr.complaint.cell.interfaceClasses;

import com.dohr.complaint.cell.modelClasses.UploadObject;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


public interface RetroApiClient {

    @Multipart
    @POST("upload.php")
    //for single file
//    Call<UploadObject> uploadFile(@Part("name") RequestBody name, @Part MultipartBody.Part file);
    Call<List<UploadObject>> uploadComplaint(@PartMap Map<String, RequestBody> data,  @Part List<MultipartBody.Part> files);


    @Multipart
    @POST("upload_files.php")
    //for multiple files
    Call<ResponseBody> uploadFiles(@Part List<MultipartBody.Part> files);
}
