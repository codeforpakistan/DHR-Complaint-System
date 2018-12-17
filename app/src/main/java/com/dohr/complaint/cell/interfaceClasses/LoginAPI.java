package com.dohr.complaint.cell.interfaceClasses;

import com.dohr.complaint.cell.LogInClasses.LogInData;
import com.dohr.complaint.cell.LogInClasses.UserProfileModle;
import com.dohr.complaint.cell.LogInClasses.VerifyUserAccount;
import com.dohr.complaint.cell.modelClasses.AnnouncementModule;
import com.dohr.complaint.cell.modelClasses.AwarenessModel;
import com.dohr.complaint.cell.modelClasses.DirctoryModule;
import com.dohr.complaint.cell.modelClasses.ForgetEmail;
import com.dohr.complaint.cell.modelClasses.ForgetPassVerification;
import com.dohr.complaint.cell.modelClasses.ForgetPasswordModel;
import com.dohr.complaint.cell.modelClasses.NewPassword;
import com.dohr.complaint.cell.modelClasses.UserRegModel;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface LoginAPI {

    @FormUrlEncoded
    @POST("login")
    Call<LogInData> checkLogin(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("forgot-password")
    Call<ForgetEmail> sendEmail(@FieldMap Map<String, String> map);

    /*@FormUrlEncoded
    @POST("register")
    Call<UserRegModel> doRegistration(@FieldMap Map<String, String> map);*/

    @Multipart
    @POST("register")
    Call<UserRegModel> doRegistration(@PartMap Map<String, RequestBody> map);



    @Multipart
    @POST("update_user_profile")
    Call<UserProfileModle> doupdate(@PartMap Map<String, RequestBody> map);

    @FormUrlEncoded
    @POST("Awareness")
    Call<AwarenessModel> getAllAwareness(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("announcements")
    Call<AnnouncementModule> getAllAnnouncement(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("phone_dir")
    Call<DirctoryModule> getAllDirctories(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("verify-forgot-password")
    Call<ForgetPassVerification> getAllForgetData(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("update-forgot-password")
    Call<NewPassword> getNewPassword(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("verify-account")
    Call<VerifyUserAccount> registerCode(@FieldMap Map<String, String> map);

   /* @Multipart
    @POST("file_complain")
        //Call<List<ComplainModle>> uploadFile(@PartMap Map<String,RequestBody> map);
    Call<List<ComplainModle>> uploadFile(@Part("name") RequestBody name, @Part MultipartBody.Part file,@PartMap Map<String,RequestBody> map);*/
}
