package com.dohr.complaint.cell.interfaceClasses;

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

public interface uploadData {
    @Multipart
    @POST()
    Call<ResponseBody> uploadFiles(@PartMap Map<String, RequestBody> data, @Part List<MultipartBody.Part> files);
}
