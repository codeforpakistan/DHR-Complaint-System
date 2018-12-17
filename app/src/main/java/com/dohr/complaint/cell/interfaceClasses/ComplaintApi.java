package com.dohr.complaint.cell.interfaceClasses;

import com.dohr.complaint.cell.UtilsClasses.Config;
import com.dohr.complaint.cell.modelClasses.ComplaintDetail;
import com.dohr.complaint.cell.modelClasses.ComplaintModel;
import com.dohr.complaint.cell.modelClasses.GetRegComplaintModle;
import com.dohr.complaint.cell.modelClasses.ListofcomplaintsDemo;
import com.dohr.complaint.cell.modelClasses.RegisterComplaintModle;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface ComplaintApi {
    @FormUrlEncoded
    @POST(Config.ComplaintEndPoint)
    Call<ComplaintModel> getList(@FieldMap Map<String, String> map);

    @Multipart
    @POST(Config.registerComplaints)
    Call<RegisterComplaintModle> uploadData(@PartMap Map<String,RequestBody> map,@Part List<MultipartBody.Part> fileList);
    //Call<RegisterComplaintModle> uploadData(@PartMap Map<String,RequestBody> map);

    @FormUrlEncoded
    @POST(Config.listOfComplaints)
    Call<ListofcomplaintsDemo> getAllComplaints(@FieldMap Map<String, String> map);

}
