package com.dohr.complaint.cell.interfaceClasses;

import com.dohr.complaint.cell.LogInClasses.LogInData;
import com.dohr.complaint.cell.javaClasses.Logindata;
import com.dohr.complaint.cell.modelClasses.ComplaintType;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ComplaintTypeApi {
    @FormUrlEncoded
    @POST("get")
    Call<ComplaintType> getComplaintTypes(@FieldMap Map<String, String> map);

}
