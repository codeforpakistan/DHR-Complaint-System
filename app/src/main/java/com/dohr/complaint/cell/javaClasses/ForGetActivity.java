package com.dohr.complaint.cell.javaClasses;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dohr.complaint.cell.LogInClasses.LogIn;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.interfaceClasses.LoginAPI;
import com.dohr.complaint.cell.modelClasses.ForgetEmail;
import com.dohr.complaint.cell.modelClasses.ForgetPassVerification;
import com.dohr.complaint.cell.modelClasses.ForgetPasswordModel;
import com.dohr.complaint.cell.modelClasses.NewPassword;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ForGetActivity extends AppCompatActivity {
    HashMap<String, String> map = new HashMap<>();
    String success;
    private static final String URL="http://banatapp.com/HumanRights/user/";
    LinearLayout card_container, card2_container, card3_container;
    private static final String TAG = "Main";
    Animation LeftSwipe,RightSwipe;
    String password,forget_data,password_data,email_data,confirm_passdata;
    EditText forget,edt_txt1,pass_worded,pass_wordcon;
    ImageView next_btn,back_bttn,submit_password;
    Snackbar snackbar;
    RelativeLayout relativeLayout;
    String data;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_get);
        forget=findViewById(R.id.forget_ed);
        card3_container=findViewById(R.id.card3_container);
        card2_container=findViewById(R.id.card2_container);
        relativeLayout=findViewById(R.id.snakbar_lay);
        card_container=findViewById(R.id.card_container);
        next_btn=findViewById(R.id.next_btn);
        back_bttn=findViewById(R.id.back_bttn);
        edt_txt1=findViewById(R.id.edt_txt1);
        pass_worded=findViewById(R.id.pass_worded);
        pass_wordcon=findViewById(R.id.pass_wordcon);
        submit_password=findViewById(R.id.submit_password);

        forget.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String forgetStr = forget.getText().toString();
                if (forgetStr.length()<4){
                    forget.setError("Enter username");
                    forget.setTextColor(Color.RED);
                }else {
                    forget.setTextColor(Color.BLACK);
                }
            }
        });

        edt_txt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String codeStr = edt_txt1.getText().toString();
                if (TextUtils.isEmpty(codeStr)){
                    edt_txt1.setError("Enter username");
                    edt_txt1.setTextColor(Color.RED);
                }else {
                    edt_txt1.setTextColor(Color.BLACK);
                }

            }
        });

        LeftSwipe = AnimationUtils.loadAnimation(this, R.anim.left_slide);
        RightSwipe = AnimationUtils.loadAnimation(this, R.anim.right_slide);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                           forget_data=forget.getText().toString();

                            if (TextUtils.isEmpty(forget_data)){
                                forget.setError("Enter Username");
                                forget.requestFocus();
                            }else
                            {
                                post();
                                card_container.setVisibility(View.GONE);
                                card2_container.setVisibility(View.VISIBLE);
                                card2_container.startAnimation(LeftSwipe);
                                count++;
                                Log.e(TAG, "swipe");
                                Log.e(TAG, "run:"+count );

                            }

                        }
                    });


                }else if(count == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            password_data=edt_txt1.getText().toString();

                            password_data=edt_txt1.getText().toString();

                            if (TextUtils.isEmpty(password_data)){
                                edt_txt1.setError("Enter Verification Code");
                                edt_txt1.requestFocus();
                            }
                            else {

                                postAgain();
                                card_container.setVisibility(View.GONE);
                                card2_container.setVisibility(View.GONE);
                                next_btn.setVisibility(View.GONE);
                                submit_password.setVisibility(View.VISIBLE);
                                card3_container.setVisibility(View.VISIBLE);
                                card3_container.startAnimation(LeftSwipe);
                                count++;
                                Log.e(TAG, "run:" + count);
                            }
                        }
                    });
                }

                }
        });

        submit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirm_passdata = pass_worded.getText().toString();
                postAgainLast();

                startActivity(new Intent(ForGetActivity.this, LogIn.class));



            }
        });

        back_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count == 2){

                    card3_container.setVisibility(View.GONE);
                    card2_container.setVisibility(View.VISIBLE);
                    card2_container.startAnimation(RightSwipe);
                    count--;
                    Log.e(TAG, "run:"+count );

                }else if(count == 1){
                    card2_container.setVisibility(View.GONE);
                    card_container.setVisibility(View.VISIBLE);
                    card_container.startAnimation(RightSwipe);
                    count--;
                    Log.e(TAG, "run:"+count );
                }
            }
        });
    }

    public void sendData(View view) {
        data=forget.getText().toString();


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                post();
            }
        });

    }
    void post(){

        map.put("email",forget_data);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginAPI rest = retrofit.create(LoginAPI.class);
        Call<ForgetEmail> call =  rest.sendEmail(map);
        call.enqueue(new Callback<ForgetEmail>() {
            @Override
            public void onResponse(Call<ForgetEmail> call, Response<ForgetEmail> response) {
                success = response.body().getSuccess();
                Log.e("Message", "onResponse: "+success );


                if (response.isSuccessful()) {

                    Log.e(TAG, "onResponse: "+ response.body().getUser().toString());
                }
            }

            @Override
            public void onFailure(Call<ForgetEmail> call, Throwable t) {
                Log.e("Message", ""+t.getMessage());
                Toast.makeText(ForGetActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void postAgain(){

        map.put("code",password_data);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginAPI rest = retrofit.create(LoginAPI.class);
        Call<ForgetPassVerification> call =  rest.getAllForgetData(map);
        call.enqueue(new Callback<ForgetPassVerification>() {
            @Override
            public void onResponse(Call<ForgetPassVerification> call, Response<ForgetPassVerification> response) {
                success = response.body().getSuccess();
                Log.e("Message", "onResponse: "+success );


                if (response.isSuccessful()) {

                   email_data=response.body().getUser().getEmail().toString();
                    Log.e("check",   response.body().getUser().getEmail());


                }
            }

            @Override
            public void onFailure(Call<ForgetPassVerification> call, Throwable t) {
                Log.e("Message", ""+t.getMessage());
                Toast.makeText(ForGetActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void postAgainLast(){

        map.put("email",email_data);
        map.put("password",confirm_passdata);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginAPI rest = retrofit.create(LoginAPI.class);
        Call<NewPassword> call =  rest.getNewPassword(map);
        call.enqueue(new Callback<NewPassword>() {
            @Override
            public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {
                success = response.body().getSuccess();
                Log.e("Message", "onResponse: "+success );


                if (response.isSuccessful()) {

                   String mm=response.body().getMessage();
                   Snackbar.make(relativeLayout," Password Updated Successfully",Snackbar.LENGTH_LONG).show();
                    Log.e("checkupdate",mm);


                }
            }

            @Override
            public void onFailure(Call<NewPassword> call, Throwable t) {
                Log.e("Message", ""+t.getMessage());
                Toast.makeText(ForGetActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
