package com.dohr.complaint.cell.LogInClasses;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.interfaceClasses.LoginAPI;
import com.dohr.complaint.cell.javaClasses.ForGetActivity;
import com.dohr.complaint.cell.javaClasses.Home;
import com.dohr.complaint.cell.javaClasses.Register;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.LOGIN_PREF;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.MOBILE_NO;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.Mobile_No;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.Name;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.TOKEN_ID;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.UserPref;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.User_id;

public class LogIn extends AppCompatActivity{

    private static final String URL="https://banatapp.com/HumanRights//user/";

    HashMap<String, String> map = new HashMap<>();
     String data_cnin,data_pass;
     EditText mobile_no,password;
    TextView register,forgetpass;
     Button login;
     CheckBox checkbox;
     String usercnic, userpassword;
    String token_key;
    //defining AwesomeValidation object
    private AwesomeValidation awesomeValidation;

    //final String regex = "^((\\+92))-{0,1}\\d{3}-{0,1}\\d{7}$|^\\d{11}$|^\\d{4}-\\d{7}$";
    //final String regex = "^[0-3]{4}-[0-9]{3}-[0-9]{4}";
    final String regex = "^\\d{4}-\\d{7}$";
    //final String regex = "^((\\+92)|(0092))-{0,1}\\d{3}-{0,1}\\d{7}$|^\\d{11}$|^\\d{4}-\\d{7}$";

    SharedPreferences loginSharedPreferences;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor loginEditor;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        sharedpreferences = getSharedPreferences(
                UserPref,
                Context.MODE_PRIVATE);
        loginSharedPreferences = getApplicationContext().getSharedPreferences(
                        LOGIN_PREF,
                        Context.MODE_PRIVATE);
        String cnic = loginSharedPreferences.getString(Mobile_No, "No Data");

        //////////////////////////////////////////////////////////////////////////////////////////
        // token key

        token_key=sharedpreferences.getString("token_key", "No Data");
        Log.e("token_id", token_key);
        if(token_key.equals("No Data"))
        {

            token_key = FirebaseInstanceId.getInstance().getToken();
        }
       // Log.e("token_id", token_key);
        //////////////////////////////////////////////////////////////////////////////////////////////
        if (!cnic.equals("No Data")) {
            Intent intent = new Intent(LogIn.this, Home.class);
            startActivity(intent);
            finish();
        }

        progressDialog = new ProgressDialog(LogIn.this);
        register = findViewById(R.id.register);
        forgetpass = findViewById(R.id.forget_pass);
        login = findViewById(R.id.login);
        mobile_no = findViewById(R.id.mobileno);
        password = findViewById(R.id.password);
        checkbox = findViewById(R.id.login_checkbox);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        mobile_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mobileNo= mobile_no.getText().toString();

                if(mobileNo.length()<13){
                    mobile_no.setTextColor(Color.RED);
                    mobile_no.setError("Enter 13 digits");
                }else {
                    mobile_no.setTextColor(Color.BLACK);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, Register.class));
            }
        });

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, ForGetActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usercnic = mobile_no.getText().toString();
                userpassword = password.getText().toString();

                if (TextUtils.isEmpty(usercnic)){
                    mobile_no.setError("Enter cnic no");
                }
                else if (TextUtils.isEmpty(userpassword)){
                    password.setError("Enter password");
                }
                else
                    {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.setTitle("Please wait");
                            progressDialog.setMessage("Authenticating...");
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.show();
                        }
                    });

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(3, TimeUnit.MINUTES)
                            .readTimeout(3, TimeUnit.MINUTES)
                            .writeTimeout(3, TimeUnit.MINUTES)
                            .build();
                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(URL).client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                    LoginAPI rest = retrofit.create(LoginAPI.class);
                    map.put("cnic",usercnic);
                    map.put("password",userpassword);
                    map.put("device_token",token_key);
                    Call<LogInData> call = rest.checkLogin(map);

                    call.enqueue(new Callback<LogInData>() {
                        @Override
                        public void onResponse(Call<LogInData> call, Response<LogInData> response) {
                            Log.e("onResponse: ", response.body().toString());
                            String success = response.body().getSuccess();
                            String status = response.body().getStatus();
                            final String message = response.body().getMessage();
                            if (response.isSuccessful()){
                                    if (success.equals("true")) {
                                        progressDialog.dismiss();
                                        String name = response.body().getUserData().getName();
                                        String mobile_no = response.body().getUserData().getMobileNo();
                                        String cnic = response.body().getUserData().getCnic();
                                        String email = response.body().getUserData().getEmail();
                                        String city = response.body().getUserData().getCity();
                                        String api_token = response.body().getUserData().getApiToken();
                                        String address = (String) response.body().getUserData().getAddress();


                                        int user_id = response.body().getUserData().getId();
                                       // Log.e("api_token", api_token);
                                        Log.e("name", "" + name);
                                        Log.e("mobile_no", "" + mobile_no);
                                        Log.e("cnic", "" + cnic);
                                        Log.e("email", "" + email);
                                        Log.e("city", "" + city);
                                        //Log.e("api_token", "" + api_token);
                                        Log.e("address", "" + address);
                                        Log.e("user_id", "" + user_id);
                                        saveUserData(name, mobile_no, cnic, email, city,api_token,user_id,address);
                                        if (checkbox.isChecked()) {
                                            loginEditor = loginSharedPreferences.edit();
                                            loginEditor.putString(Mobile_No, mobile_no);
                                            //loginEditor.putString(TOKEN_ID, api_token);
                                            loginEditor.putString("user_id", String.valueOf(user_id));
                                            loginEditor.commit();
                                        }
                                    }
                                    else if(success.equalsIgnoreCase("false"))
                                    {
                                        progressDialog.dismiss();
                                        Toast.makeText(LogIn.this, message, Toast.LENGTH_LONG).show();

                                    }
                            }
                            
                        }

                        @Override
                        public void onFailure(Call<LogInData> call, Throwable t) {
                            Log.e("fail", "onFailure: "+t);

                        }
                    });
                }
            }

        });

        /*Validating Form*/

//initializing awesomevalidation object
        /*
         * The library provides 3 types of validation
         * BASIC
         * COLORATION
         * UNDERLABEL
         * */
       /* awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //adding validation to edittexts
        //awesomeValidation.addValidation(this, R.id.editTextName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        //awesomeValidation.addValidation(this, R.id.editTextEmail, Patterns.EMAIL_ADDRESS, R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.mobileno, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        awesomeValidation.addValidation(this, R.id.password, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.passworderror);
        //awesomeValidation.addValidation(this, R.id.editTextAge, Range.closed(13, 60), R.string.ageerror);

      */  //buttonSubmit.setOnClickListener(this);
//}

    }

    private void saveUserData(String name, String mobile_no, String cnic, String email, String city, String api_token, int user_id,String address) {

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, name);
        editor.putString(Mobile_No, mobile_no);
        editor.putString(Cnic, cnic);
        editor.putString(EMAIL, email);
        editor.putString(City, city);
        editor.putString(Api_token, api_token);
        editor.putString(User_id, String.valueOf(user_id));
        editor.putString(ADDRESS, address);
        editor.commit();
        Log.e("saveuserdata", "saveUserData: "+api_token +    +user_id);
        Intent intent = new Intent(LogIn.this, Home.class);
        startActivity(intent);
        finish();
    }

    private void addValidation() {
        Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();
        awesomeValidation.addValidation(this, R.id.mobileno, "^((\\+92)|(0092))-{0,1}\\d{3}-{0,1}\\d{7}$|^\\d{11}$|^\\d{4}-\\d{7}$", R.string.mobileerror);
    }

    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();

            //process the data further
        }
    }


}

