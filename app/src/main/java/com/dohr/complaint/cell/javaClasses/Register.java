package com.dohr.complaint.cell.javaClasses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.dohr.complaint.cell.HelperClasses.AppActivity;
import com.dohr.complaint.cell.LogInClasses.VerifyUserAccount;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.UtilsClasses.UserPrefence;
import com.dohr.complaint.cell.interfaceClasses.LoginAPI;
import com.dohr.complaint.cell.modelClasses.Registered;
import com.dohr.complaint.cell.modelClasses.User;
import com.dohr.complaint.cell.modelClasses.UserRegModel;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;


import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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

public class Register extends AppCompatActivity{


    String tv_name,tv_moblie,tv_email,tv_cnic,tv_city,tv_password,tv_gender,tv_fthername,tv_address;
    private static final String URL="https://banatapp.com/HumanRights/user/";
    Button register;
    EditText name,mobileno,email,cnic,city,password,father_name,Address;
    HashMap<String, RequestBody> map = new HashMap<>();
    SharedPreferences sharedpreferences;
    String token_key,userid;
    RadioGroup rg1;
    RadioButton rb1;
    RadioButton radioButton,radioButton2,radioButton3;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedpreferences = getSharedPreferences(UserPref,
                Context.MODE_PRIVATE);
        final String userId = sharedpreferences.getString(User_id,"No data");
        token_key=sharedpreferences.getString("token_key", "No Data");
        if(token_key.equals("No Data"))
        {

            token_key = FirebaseInstanceId.getInstance().getToken();
            Log.e("token_id", token_key);
        }


             rg1=findViewById(R.id.radioGroup);
       /* if(!userId.equals("No data")){
            startActivity(new Intent(Register.this,Home.class));
            finish();

        }else{*/
        register = findViewById(R.id.register);
        name = findViewById(R.id.user_name);
        mobileno = findViewById(R.id.mobileno);
        email = findViewById(R.id.email);
        cnic = findViewById(R.id.cnic);
        city = findViewById(R.id.city);
        password = findViewById(R.id.password);
        rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.radioButton) {
                    tv_gender = "Female";
                } else if(checkedId == R.id.radioButton2) {
                    tv_gender = "Male";
                }else if(checkedId == R.id.radioButton3) {
                    tv_gender = "Other";
                }
                else {

                }
            }

        });


        /*int selectedId = rg1.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioButton =  findViewById(selectedId);
        radioButton2 =  findViewById(selectedId);
        radioButton3 =  findViewById(selectedId);
*/


        /*if(radioButton.isChecked()) {
            tv_gender = radioButton.getText().toString();

        }
        else if(radioButton2.isChecked())
        {
            tv_gender=radioButton2.getText().toString();
        }
        else
        {
            tv_gender=radioButton3.getText().toString();
        }*/
        father_name = findViewById(R.id.father_name);
        Address = findViewById(R.id.Address);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String nameStr = name.getText().toString();
                if (nameStr.length()<4){
                    name.setError("Enter username");
                    name.setTextColor(Color.RED);
                }else {
                    name.setTextColor(Color.BLACK);
                }
            }
        });

        mobileno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mobile = mobileno.getText().toString();
                if (mobile.length()<11){
                    mobileno.setError("Enter Mobile Number");
                    mobileno.setTextColor(Color.RED);
                }else {
                    mobileno.setTextColor(Color.BLACK);
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String Email = email.getText().toString();
                if (Email.length()<1){
                    email.setError("Enter Email Address");
                    email.setTextColor(Color.RED);
                }else {
                    email.setTextColor(Color.BLACK);
                }
            }
        });

        cnic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String CNIC = cnic.getText().toString();
                if (CNIC.length()<13){
                    cnic.setError("Enter Email Address");
                    cnic.setTextColor(Color.RED);
                }else {
                    cnic.setTextColor(Color.BLACK);
                }
            }
        });

        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String CITY = city.getText().toString();
                if (CITY.length()<4){
                    city.setError("Enter Valid City Name");
                    city.setTextColor(Color.RED);
                }else {
                    city.setTextColor(Color.BLACK);
                }
            }
        });



//getting data by clicking the click button e.g register

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(Register.this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();

                tv_name=name.getText().toString();
                tv_email=email.getText().toString();
                tv_city=city.getText().toString();
                tv_cnic=cnic.getText().toString();
                tv_moblie=mobileno.getText().toString();
                tv_password=password.getText().toString();
                tv_fthername=father_name.getText().toString();
                tv_address=Address.getText().toString();

                Log.e("check", "onClick: "+tv_name+" "+tv_email+" "+tv_cnic+" "+tv_cnic+" "+tv_moblie+" "+tv_password+" "+tv_fthername+" "+tv_address );
                if (TextUtils.isEmpty(tv_name)){
                    name.setError("Enter Username");
                    name.requestFocus();
                }else if (TextUtils.isEmpty(tv_email))
                {
                    email.setError("Enter email address");
                    email.requestFocus();
                }
                else if (TextUtils.isEmpty(tv_moblie)){

                    city.setError("Enter your City name");
                    city.requestFocus();
                }
                else if (TextUtils.isEmpty(tv_cnic)){

                    cnic.setError("Enter your CNIC Number");
                    cnic.requestFocus();
                }
                else if (TextUtils.isEmpty(tv_city)){

                    mobileno.setError("Enter your Mobile Number");
                    mobileno.requestFocus();
                }
                else if (TextUtils.isEmpty(tv_fthername)){

                    father_name.setError("Enter your Father name");
                    father_name.requestFocus();
                }  else if (TextUtils.isEmpty(tv_address)){

                    Address.setError("Enter your address");
                    Address.requestFocus();
                }

                else {

                    map.put("name", createPartFromString(tv_name));
                    map.put("mobile_no", createPartFromString(tv_moblie));
                    map.put("cnic", createPartFromString(tv_cnic));
                    map.put("email", createPartFromString(tv_email));
                    map.put("city", createPartFromString(tv_city));
                    map.put("password",createPartFromString(tv_password) );
                    map.put("device_token",createPartFromString(token_key) );
                    map.put("father_name", createPartFromString(tv_fthername));
                    map.put("gender", createPartFromString(tv_gender));
                    Log.e( "tv_gender: ", tv_gender);
                    map.put("address", createPartFromString(tv_address));

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(URL)
                            .addConverterFactory(MoshiConverterFactory.create())
                            .build();
                    LoginAPI rest = retrofit.create(LoginAPI.class);
                    Call<UserRegModel> call =  rest.doRegistration(map);
                    call.enqueue(new Callback<UserRegModel>() {
                        @Override
                        public void onResponse(Call<UserRegModel> call, Response<UserRegModel> response) {
                            if(response.isSuccessful())
                            {
                                String status = response.body().getSuccess();
                                Log.e("status", "onResponse: "+status );
                                if (status.equals("true")){


                                 /*   String name = response.body().getUserData().getName();
                                    String mobile_no = response.body().getUserData().getMobileNo();
                                    String cnic = response.body().getUserData().getCnic();
                                    String email = response.body().getUserData().getEmail();
                                    String city = response.body().getUserData().getCity();
                                    String api_token = response.body().getUserData().getApiToken();
                                    String father_name = response.body().getUserData().getFatherName().toString();
                                    String gender = response.body().getUserData().getGender().toString();
                                    String address = response.body().getUserData().getAddress().toString();
                                    int user_id = response.body().getUserData().getId();
                                    Log.e("name", name );
                                    Log.e("mobile_no", mobile_no );
                                    Log.e("cnic", cnic );
                                    Log.e("email", email );
                                    Log.e("city", city );
                                    Log.e("device_token", api_token );
                                    Log.e("user_id", String.valueOf(user_id));*/
                                    //saveUserData(name,mobile_no,cnic,email,city,api_token,user_id,father_name,gender,address);

                                   Intent intent=new Intent(Register.this, UserVerification.class);
                                    startActivity(intent);


                                }else {
                                    Toast.makeText(Register.this, "Already Exist!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<UserRegModel> call, Throwable t) {
                            Log.e("Message", ""+t.getMessage());
                            Toast.makeText(Register.this, "Network error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //submitForm();
                        //getting data from services
            }
        });


    }






       /* setContentView(R.layout.activity_regist);
        ed_name=findViewById(R.id.et_name);
        ed_phone_number=findViewById(R.id.et_phone_number);
        ed_cnic=findViewById(R.id.et_cnic);
        ed_emaill=findViewById(R.id.et_email);

    }


    public void adduser(View view) {
   *//* String name,phonenumber,cnic,email;
        name=ed_name.getText().toString();
        phonenumber=ed_phone_number.getText().toString();
        cnic=ed_cnic.getText().toString();
        email=ed_emaill.getText().toString();*//*


        map.put("userTitle","test889");
        map.put("userPassword","test889");
        map.put("userEmail","test88@yahoo.com");
        map.put("cnic","1234567892");
        map.put("contactNumber","123456789");
        map.put("district_id","2");
        map.put("address","abcdasdgg");
        map.put("gender","female");
        Log.e("post data",map.toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        RestApi rest = retrofit.create(RestApi.class);
        Call<List<Registered>> call =  rest.doRegistration(map);
        call.enqueue(new Callback<List<Registered>>() {
            @Override
            public void onResponse(Call<List<Registered>> call, Response<List<Registered>> response) {

                Log.e("Response", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Registered>> call, Throwable t) {
                Log.e("Message", ""+t.getMessage());
            }
        });




    }*/


/*}*/
    private void saveUserData(String name, String mobile_no, String cnic, String email, String city, String api_token, int user_id,String fthername, String gender, String address) {

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, name);
        editor.putString(Mobile_No, mobile_no);
        editor.putString(Cnic, cnic);
        editor.putString(EMAIL, email);
        editor.putString(City, city);
        editor.putString(Api_token, api_token);
        editor.putString(FATHER, fthername);
        editor.putString(GENDER, gender);
        editor.putString(ADDRESS, address);
        editor.putString(User_id, String.valueOf(user_id));
        editor.commit();
        startActivity(new Intent(Register.this,Home.class));
        Toast.makeText(this, "Data send successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    tv_gender="male";
                Log.e("onRadioButtonClicked: ",tv_gender);
                    break;
            case R.id.radioButton2:
                if (checked)
                    tv_gender="female";
                Log.e("onRadioButtonClicked: ",tv_gender);
                    break;
                case R.id.radioButton3:
                if (checked)
                    tv_gender="other";
                    Log.e("onRadioButtonClicked: ",tv_gender);
                    break;
        }
    }

    @NonNull
    private RequestBody createPartFromString(String val) {
        return RequestBody.create(okhttp3.MultipartBody.FORM,  val);
    }

}