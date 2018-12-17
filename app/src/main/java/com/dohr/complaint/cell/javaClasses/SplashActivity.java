package com.dohr.complaint.cell.javaClasses;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.dohr.complaint.cell.LogInClasses.LogIn;
import com.dohr.complaint.cell.R;

import com.dohr.complaint.cell.RoomSqliteDB.DhrDatabase;
import com.dohr.complaint.cell.UtilsClasses.Config;
import com.dohr.complaint.cell.interfaceClasses.ComplaintApi;
import com.dohr.complaint.cell.interfaceClasses.SubComplaintApi;
import com.dohr.complaint.cell.interfaceClasses.SubComplaintApi2;
import com.dohr.complaint.cell.modelClasses.AllCategory;
import com.dohr.complaint.cell.modelClasses.ComplaintModel;
import com.dohr.complaint.cell.modelClasses.SubComplaintModel;
import com.dohr.complaint.cell.modelClasses.SubComplaintModel2;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.Api_token;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.LOGIN_PREF;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.MOBILE_NO;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.UserPref;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.User_id;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "splash";
    private Retrofit retrofit;
    private static final String DATABASE_NAME = "demo_db";
    private DhrDatabase mDatabase;
    SharedPreferences sharedpreferences,sharedpreferences2;
    String mobileNo,api_token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedpreferences2 = getSharedPreferences(UserPref,
                Context.MODE_PRIVATE);
        sharedpreferences = getSharedPreferences(LOGIN_PREF,
                Context.MODE_PRIVATE);
        api_token = sharedpreferences2.getString(Api_token, "No Data");
        Log.e(TAG, "onCreate: "+api_token);
        mobileNo = sharedpreferences.getString(MOBILE_NO,"No data");
        setUi();

    }

    private void setUi() {
        mDatabase = Room.databaseBuilder(getApplicationContext(),
                DhrDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isNetworkAvailable()){
                    getComplaintsCategories();
                    getSubComplaintCategories();
                    getSubComplaintCategories2();
                }else {
                    if (mDatabase.daoAccess().fetchSubComplaintList().size() > 0){
                        if (!mobileNo.equals("No data")){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(4000);
                                        startActivity(new Intent(SplashActivity.this, Home.class));
                                        finish();
                                        Log.e(TAG, "for home: " );
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                        }else {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(4000);
                                        startActivity(new Intent(SplashActivity.this, LogIn.class));
                                        finish();
                                        Log.e(TAG, "for login: " );
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();


                        }
                    }else {
                        Toast.makeText(SplashActivity.this, "Internet Not Available", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        }).start();
    }



    private void getSubComplaintCategories() {
        Map<String, String> map = new HashMap<>();
        map.put("api_token", api_token);
        map.put("category_id", "2");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SubComplaintApi api = retrofit.create(SubComplaintApi.class);
        Call<SubComplaintModel> call = api.getSubList(map);
        call.enqueue(new Callback<SubComplaintModel>() {
            @Override
            public void onResponse(Call<SubComplaintModel> call, final Response<SubComplaintModel> response) {
                String success = response.body().getSuccess();
                Log.e(TAG, "onResponse: "+success );
                if (response.isSuccessful()){
                    if (success.equals("true")){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                               mDatabase.daoAccess().deleteSubCategory();
                                final List<SubComplaintModel.SubCategory> list = response.body().getSubCategories();
                                Log.e(TAG, ""+list.size());
                                Log.e(TAG, "list "+list.toString());
                                mDatabase.daoAccess () . insertSubComplaintList (list);
                                /*if (!mobileNo.equals("No data")){
                                    startActivity(new Intent(SplashActivity.this, Home.class));
                                    finish();
                                    Log.e(TAG, "for home: " );
                                }else {
                                    startActivity(new Intent(SplashActivity.this, LogIn.class));
                                    finish();
                                    Log.e(TAG, "for login: " );

                                }*/
                                /*startActivity(new Intent(SplashActivity.this, LogIn.class));
                                finish();*/

                                //there
                                //g
                                //insert tu kar diya 3rd layer phir?
                                //first is ko check karty hai
                                //then sho karty hai ok
                                //app hai mobile mai existing ?
                                //hn
                                //uninstall karook
                            }
                        }) .start();
                    }
                }
            }

            @Override
            public void onFailure(Call<SubComplaintModel> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage() );
            }
        });
    }
    private void getSubComplaintCategories2() {

        //kia howa? charger lagaya there ? g splash say agay ni jrai app :( han han i know
        Map<String, String> map = new HashMap<>();
        map.put("api_token", api_token);
        map.put("sub_category_id", "1");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SubComplaintApi2 api = retrofit.create(SubComplaintApi2.class);
        Call<SubComplaintModel2> call = api.getSubList2(map);
        call.enqueue(new Callback<SubComplaintModel2>() {
            @Override
            public void onResponse(Call<SubComplaintModel2> call, final Response<SubComplaintModel2> response) {
                String success = response.body().getSuccess();
                Log.e(TAG, "onResponse: "+success );
                if (response.isSuccessful()){
                    if (success.equals("true")){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                               mDatabase.daoAccess().deleteSubCategory2();
                                final List<SubComplaintModel2.SubCategory2> list = response.body().getSubCategories();
                                Log.e(TAG, ""+list.size());
                                Log.e(TAG, "sublist2 "+list.toString());
                                mDatabase.daoAccess () . insertSubComplaintList2 (list);
                                if (!mobileNo.equals("No data")){
                                    startActivity(new Intent(SplashActivity.this, Home.class));
                                    finish();
                                    Log.e(TAG, "for home: " );
                                }else {
                                    startActivity(new Intent(SplashActivity.this, LogIn.class));
                                    finish();
                                    Log.e(TAG, "for login: " );

                                }
                                /*startActivity(new Intent(SplashActivity.this, LogIn.class));
                                finish();*/
                            }
                        }) .start();
                    }
                }
            }

            @Override
            public void onFailure(Call<SubComplaintModel2> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage() );
            }
        });
    }

    private void getComplaintsCategories() {
        Map<String, String> map = new HashMap<>();
      //  map.put("api_token", "xNI2rtUd8fs4DChNRfMNHg9SzLYJgzzKk0EqQ2PFYXpxosgR3VyIUn9ZjhTx");
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ComplaintApi api = retrofit.create(ComplaintApi.class);
        Call<ComplaintModel> call = api.getList(map);
        call.enqueue(new Callback<ComplaintModel>() {
            @Override
            public void onResponse(Call<ComplaintModel> call, final Response<ComplaintModel> response) {


                String success = response.body().getSuccess();
                Log.e(TAG, "onResponse: "+success );
                if (response.isSuccessful()){
                    if (success.equals("true")){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mDatabase.daoAccess().deleteCategory();
                               List<ComplaintModel.AllCategory> list = response.body().getAllCategories();
                                Log.e(TAG, ""+list.size());
                                Log.e(TAG, "sublist "+list.toString());
                                mDatabase.daoAccess () . insertComplaintList (list);

                            }
                        }) .start();
                    }
                }
            }
            @Override
            public void onFailure(Call<ComplaintModel> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage() );
            }
        });
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected())
        {
            isAvailable = true;

        }
        return isAvailable;
    }
}
