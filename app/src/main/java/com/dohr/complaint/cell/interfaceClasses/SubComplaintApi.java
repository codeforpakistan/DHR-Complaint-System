package com.dohr.complaint.cell.interfaceClasses;


import com.dohr.complaint.cell.UtilsClasses.Config;
import com.dohr.complaint.cell.modelClasses.SubComplaintModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SubComplaintApi {
    @FormUrlEncoded
    @POST(Config.SubComplaintEndPoint)
    Call<SubComplaintModel> getSubList(@FieldMap Map<String, String> map);
}
