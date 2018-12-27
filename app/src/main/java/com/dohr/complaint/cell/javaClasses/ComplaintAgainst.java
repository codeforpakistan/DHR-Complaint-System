package com.dohr.complaint.cell.javaClasses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dohr.complaint.cell.R;

import java.util.jar.Attributes;

import butterknife.BindView;

public class ComplaintAgainst extends AppCompatActivity {

    ImageView nextbttn,backbtn;
    EditText Name,mobilenumber,area,email,address;
    String ComplaintType, subcomplaint, subject, details;
    SharedPreferences editformSharePreferencestwo;
    SharedPreferences.Editor editformEditortwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_against);

        nextbttn = findViewById(R.id.nextbttn);
        backbtn = findViewById(R.id.backbtn);
        Name = findViewById(R.id.Name);
        mobilenumber = findViewById(R.id.mobilenumber);
        area = findViewById(R.id.area);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        Intent intent = getIntent();
        ComplaintType = intent.getStringExtra("ComplaintType");
        subcomplaint = intent.getStringExtra("subcomplaint");
        subject = intent.getStringExtra("Subject");
        details = intent.getStringExtra("Details");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        editformSharePreferencestwo=getApplicationContext().getSharedPreferences("EditDatatwo",MODE_PRIVATE);
        String  sh_name=editformSharePreferencestwo.getString("name","no data");
        String  sh_mobileno=editformSharePreferencestwo.getString("mobileno","no data");
        String  sh_location=editformSharePreferencestwo.getString("location","no data");
        String  sh_PersonAddress=editformSharePreferencestwo.getString("PersonAddress","no data");
        String  sh_personemail=editformSharePreferencestwo.getString("personemail","no data");
        if(sh_name != "no data")
        {
          Name.setText(sh_name);
          mobilenumber.setText(sh_mobileno);
          area.setText(sh_location);
          email.setText(sh_personemail);
          address.setText(sh_PersonAddress);
            Log.e("sharedata", "shareprefrance "+sh_name+"  "+sh_mobileno+"  "+sh_location+" "+sh_personemail+" "+sh_PersonAddress);
            editformEditortwo = editformSharePreferencestwo.edit();
            editformEditortwo.clear();
            editformEditortwo.commit();
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////




        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComplaintAgainst.this, NewComplaint.class));
            }
        });

        nextbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = Name.getText().toString();
                String mobileno = mobilenumber.getText().toString();
                String location = area.getText().toString();
                String personemail = email.getText().toString();
                String PersonAddress = address.getText().toString();

                if (TextUtils.isEmpty(name))
                {
                    /*Name.setError("Please enter your name");
                    Name.requestFocus();*/
                    name = "N/A";
                }
                else if (TextUtils.isEmpty(mobileno)){
                    /*mobilenumber.setError("Please enter your mobile number");
                    mobilenumber.requestFocus();*/
                    mobileno = "N/A";
                }
                else
                    if (TextUtils.isEmpty(location)){
                       /* area.setError("Please enter your area name");
                        area.requestFocus();*/
                        location = "N/A";
                    }
                    else if (TextUtils.isEmpty(personemail)){
                        /*email.setError("Please enter your email");
                        email.requestFocus();*/
                        personemail = "N/A";
                    }
                    else if (TextUtils.isEmpty(PersonAddress)){
                        /*address.setError("Please enter your address");
                        address.requestFocus();*/
                        PersonAddress = "N/A";
                    }

                    else
                    {

                    }

                Intent intent= new Intent(ComplaintAgainst.this, AttachmentSubmit.class);
                intent.putExtra("ComplaintType",ComplaintType);
                intent.putExtra("subcomplaint",subcomplaint);
                intent.putExtra("subject",subject);
                intent.putExtra("details",details);

                intent.putExtra("name",name);
                intent.putExtra("mobileno",mobileno);
                intent.putExtra("location",location);
                intent.putExtra("personemail",personemail);
                intent.putExtra("PersonAddress",PersonAddress);

                startActivity(intent);

            }
        });


    }
}
