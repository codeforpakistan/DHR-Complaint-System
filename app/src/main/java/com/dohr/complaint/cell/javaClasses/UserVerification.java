package com.dohr.complaint.cell.javaClasses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dohr.complaint.cell.LogInClasses.VerifyUserAccount;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.interfaceClasses.LoginAPI;

import java.util.HashMap;

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
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.FATHER;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.GENDER;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.Mobile_No;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.Name;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.UserPref;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.User_id;

public class UserVerification extends AppCompatActivity {
    private static final String URL="https://banatapp.com/HumanRights/user/";
    String name,father_name,mobile_no,email,cnic,gender,city,address,userId,api_token;
    SharedPreferences sharedpreferences;
    HashMap<String, String> map = new HashMap<>();
    EditText edt_txt1,edt_txt2,edt_txt3,edt_txt4,edit_text1;
    String data_one,data_two,data_three,data_four,all_data;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_verification);
        sharedpreferences = getSharedPreferences(UserPref,
                Context.MODE_PRIVATE);
        editor= sharedpreferences.edit();
      /*  edt_txt4 = findViewById(R.id.edt_txt4);
        edt_txt3 = findViewById(R.id.edt_txt3);
        edt_txt2 = findViewById(R.id.edt_txt2);
        edt_txt1 = findViewById(R.id.edt_txt1);*/
        edit_text1 = findViewById(R.id.edit_text1);


    }



    private void saveUserData(String name, String mobile_no, String cnic, String email, String city, String api_token, String user_id,String fthername, String gender, String address) {


        editor.putString(Name, name);
        editor.putString(Mobile_No, mobile_no);
        editor.putString(Cnic, cnic);
        editor.putString(EMAIL, email);
        editor.putString(City, city);
        editor.putString(Api_token, api_token);
        editor.putString(FATHER, fthername);
        editor.putString(GENDER, gender);
        editor.putString(ADDRESS, address);
        editor.putString(User_id, user_id);
        editor.commit();
        Log.e("Sharedprefrences","saveUserData: ");
        Log.e("Sharedprefrences",name+" "+mobile_no+" "+cnic+" "+email+" "+city);
        Log.e("Sharedprefrences",api_token+" "+user_id+" "+fthername+" "+gender+" "+address);
    }

    public void dosomething(View view) {

/*
        data_one=edt_txt1.getText().toString();
        data_two=edt_txt2.getText().toString();
        data_three=edt_txt3.getText().toString();
        data_four=edt_txt4.getText().toString();
        all_data=data_one+data_two+data_three+data_four;
        Log.e( "dosomething: ",all_data );*/

        data_one=edit_text1.getText().toString();

runOnUiThread(new Runnable() {
    @Override
    public void run() {
        postData();
    }
});


    }

    void postData()
    {



        map.put("code", data_one);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginAPI rest = retrofit.create(LoginAPI.class);
        Call<VerifyUserAccount> call =  rest.registerCode(map);
        call.enqueue(new Callback<VerifyUserAccount>() {
            @Override
            public void onResponse(Call<VerifyUserAccount> call, Response<VerifyUserAccount> response) {
                if(response.isSuccessful())
                {

                    String status = response.body().getSuccess();
                    Log.e("status", "onResponse: "+status );
                    if (status.equals("true")){

                        name =response.body().getUser().getName();
                        mobile_no= response.body().getUser().getMobileNo();
                        cnic  =response.body().getUser().getCnic();
                        email =response.body().getUser().getEmail();
                        city =response.body().getUser().getCity();
                        api_token=response.body().getUser().getApiToken();
                        userId= response.body().getUser().getId();
                        father_name=response.body().getUser().getFatherName().toString();
                        gender= response.body().getUser().getGender().toString();
                        address =response.body().getUser().getAddress().toString();
                        Log.e("onResponse: ", name+" "+mobile_no+" "+cnic+" "+email+" "+city);
                        Log.e("onResponse: ", api_token+" "+userId+" "+father_name+" "+gender+" "+address);

                        saveUserData(name,mobile_no,cnic,email,city,api_token,userId,father_name,gender,address);

                        Intent intent=new Intent(UserVerification.this,Home.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(UserVerification.this, "Already Exist!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<VerifyUserAccount> call, Throwable t) {
                Log.e("Message", ""+t.getMessage());
                Toast.makeText(UserVerification.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
