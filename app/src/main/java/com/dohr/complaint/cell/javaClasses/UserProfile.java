package com.dohr.complaint.cell.javaClasses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dohr.complaint.cell.LogInClasses.User;
import com.dohr.complaint.cell.LogInClasses.UserProfileModle;
import com.dohr.complaint.cell.modelClasses.UpdataModule;
import com.dohr.complaint.cell.LogInClasses.LogIn;
import com.dohr.complaint.cell.LogInClasses.LogInData;
import com.dohr.complaint.cell.LogInClasses.UserDataLogIn;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.interfaceClasses.LoginAPI;


import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.ADDRESS;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.Api_token;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.City;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.Cnic;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.EMAIL;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.MOBILE_NO;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.Mobile_No;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.Name;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.TOKEN_ID;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.UserPref;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.User_id;

public class UserProfile extends AppCompatActivity {
    private static final String URL="https://banatapp.com/HumanRights/user/";
    LinearLayout back_btn;
    Button update_profile;
    SharedPreferences sharedpreferences;
   SharedPreferences.Editor editor;
    EditText mobile_no, city_name, address_name, email_address, password_user;
    String up_date_mobileno,up_date_city,up_date_address,up_date_email,up_date_password,userId,api_token;
    HashMap<String, RequestBody> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        sharedpreferences = getApplicationContext().getSharedPreferences(
                UserPref,
                Context.MODE_PRIVATE);
        back_btn = findViewById(R.id.back_btn);
        mobile_no = findViewById(R.id.mobile_no);
        city_name = findViewById(R.id.city_name);
        address_name = findViewById(R.id.address_name);
        email_address = findViewById(R.id.email_address);
        password_user = findViewById(R.id.password_user);
        update_profile = findViewById(R.id.update_profile);

        String name = sharedpreferences.getString(Name, "No Data");
         userId = sharedpreferences.getString(User_id, "No Data");
        api_token = sharedpreferences.getString(Api_token, "No Data");
        String mobilenumber = sharedpreferences.getString(Mobile_No, "No Data");
        String email = sharedpreferences.getString(EMAIL, "No Data");
        String city = sharedpreferences.getString(City, "No Data");
        String address = sharedpreferences.getString(ADDRESS, "No Data");

        mobile_no.setText(mobilenumber);
        city_name.setText(city);
        address_name.setText(address);
        email_address.setText(email);

        Log.e( "name: ",userId +" "+api_token+" "+email+" "+city+" "+address);

        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                up_date_mobileno= mobile_no.getText().toString();
                up_date_city= city_name.getText().toString();
                up_date_address= address_name.getText().toString();
                up_date_email= email_address.getText().toString();
                up_date_password = password_user.getText().toString();

                map.put("user_id",createPartFromString(userId));
                map.put("api_token",createPartFromString(api_token));
                map.put("mobile_no",createPartFromString(up_date_mobileno));
                map.put("email",createPartFromString(up_date_email));
                map.put("password",createPartFromString(up_date_password));
                map.put("city",createPartFromString(up_date_city));
                map.put("address",createPartFromString(up_date_address));
                postData();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(UserProfile.this, Home.class));

            }
        });
    }

    void postData()
    {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginAPI rest = retrofit.create(LoginAPI.class);

        Call<UserProfileModle> call = rest.doupdate(map);
        call.enqueue(new Callback<UserProfileModle>() {
            @Override
            public void onResponse(Call<UserProfileModle> call, Response<UserProfileModle> response) {
                /*String success = response.body().getSuccess();
                String status = response.body().getStatus();
                String message = response.body().getMessage();*/

                Log.e("response",response.toString() );
                Log.e("response body",response.body().toString() );

               /* if (response.isSuccessful()){
                    if (success.equals(success)) {



                       *//* Toast.makeText(UserProfile.this, "updated", Toast.LENGTH_SHORT).show();
                        User userData=response.body().getUser();




                    String mob=userData.getMobileNo();
                     String cnic = userData.getCnic();
                     String email=userData.getEmail();
                       String city= userData.getCity();
                       String name=userData.getName();*//*
                       // saveUserData(name,mob,cnic, email,city, address);

                        up_date_mobileno= mobile_no.getText().toString();
                        up_date_city= city_name.getText().toString();
                        up_date_address= address_name.getText().toString();
                        up_date_email= email_address.getText().toString();
                        up_date_password = password_user.getText().toString();

                        //saveUserData(name,mob,cnic, email,city);
                        saveUserData(up_date_mobileno,up_date_city,up_date_address, up_date_email,up_date_password);

                    }
                }*/

            }

            @Override
            public void onFailure(Call<UserProfileModle> call, Throwable t) {
                Log.e("fail", "onFailure: "+t);

            }
        });
    }




    private void saveUserData(String up_date_mobileno, String up_date_city, String up_date_address, String up_date_email, String up_date_password) {

         editor = sharedpreferences.edit();

        editor.putString(Mobile_No, up_date_mobileno);
        editor.putString(EMAIL, up_date_email);
        editor.putString(City, up_date_city);
        editor.putString(ADDRESS, up_date_address);
        editor.commit();


       String check= sharedpreferences.getString(Mobile_No,"NO DATA");
        Log.e("saveuserdata", "saveUserData: "+check);


    }

    @NonNull
    private RequestBody createPartFromString(String val) {
        return RequestBody.create(okhttp3.MultipartBody.FORM,  val);
    }

    }



