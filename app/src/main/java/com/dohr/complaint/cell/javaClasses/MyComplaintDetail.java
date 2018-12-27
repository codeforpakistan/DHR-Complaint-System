package com.dohr.complaint.cell.javaClasses;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dohr.complaint.cell.LogInClasses.LogIn;
import com.dohr.complaint.cell.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import static com.dohr.complaint.cell.UtilsClasses.Config.BaseUrl;

public class MyComplaintDetail extends AppCompatActivity {

    ImageView backbtn;
    String my_ComplaintStatus,my_DeptName,my_PersonPhoneNumber,my_Location,my_PersonEmail,my_PersonAddress,
            my_ComplaintType,my_ComplaintSubType,my_Subject,my_Detail,my_Image;
    ImageView imageView;
    TextView status, DeptName,PhoneNo, Location, User_Email, Address, complaintType, subComplaintType, subject,details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_complaint_detail);
        backbtn = findViewById(R.id.backbtn);
        imageView=findViewById(R.id.image1);
        status=findViewById(R.id.title1);
        DeptName=findViewById(R.id.dept_name);
        PhoneNo=findViewById(R.id.phone_no);
        Location=findViewById(R.id.city);
        User_Email=findViewById(R.id.User_email);
        Address=findViewById(R.id.complete_address);
        complaintType=findViewById(R.id.complaint_type);
        subComplaintType=findViewById(R.id.subcomplaint_type);
        subject=findViewById(R.id.subject);
        details=findViewById(R.id.details_complaint);

        //getting data through intent
        Intent intent=getIntent();
        Bundle bundle= intent.getExtras();

       if (bundle != null)
       {   my_ComplaintStatus=bundle.getString("st_ComplaintStatus");
           my_DeptName=bundle.getString("st_DeptName");
           my_PersonPhoneNumber=bundle.getString("st_PersonPhoneNumber");
           my_Location=bundle.getString("st_Location");
           my_PersonEmail=bundle.getString("st_PersonEmail");
           my_PersonAddress=bundle.getString("st_PersonAddress");
           my_ComplaintType=bundle.getString("st_ComplaintType");
           my_ComplaintSubType=bundle.getString("st_ComplaintSubType");
           my_Subject=bundle.getString("st_Subject");
           my_Detail=bundle.getString("st_Detail");
           my_Image=bundle.getString("st_Image");
          // my_Image="1 Diego Maradona , Footballer Argentina";
           Log.e("my_Image", my_Image);
       }
       //setting text on textviews
       status.setText(my_ComplaintStatus);
       DeptName.setText(my_DeptName);
       PhoneNo.setText(my_PersonPhoneNumber);
       Location.setText(my_Location);
       User_Email.setText(my_PersonEmail);
       Address.setText(my_PersonAddress);
       complaintType.setText(my_ComplaintType);
       subComplaintType.setText(my_ComplaintSubType);
       subject.setText(my_Subject);
       details.setText(my_Detail);
        ArrayList<String> arrayList = new ArrayList<>();
        String arr [] = my_Image.split(",");
        for (int i = 0; i < arr.length; i++) {
            //String str = arr[i].trim();
            //Log.e("shah", str.trim());
            arrayList.add(arr[i].trim());
            Log.e("arr", ""+arrayList.get(i) );

        }

        //converting string into uri
        Picasso.with(this).load(BaseUrl+"/public/uploads/complaints_data/"+my_Image).resize(300,300).into(imageView);



        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MyComplaintDetail.this, ComplaintStatus.class));
                finish();
            }
        });

    }
}
