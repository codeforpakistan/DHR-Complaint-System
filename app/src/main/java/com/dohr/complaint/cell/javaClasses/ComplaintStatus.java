package com.dohr.complaint.cell.javaClasses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.UtilsClasses.Config;
import com.dohr.complaint.cell.UtilsClasses.UserPrefence;
import com.dohr.complaint.cell.interfaceClasses.ComplaintApi;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.RecyclerTouchListener;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.RecyclerViewAdapter;
import com.dohr.complaint.cell.modelClasses.ComplaintDetail;
import com.dohr.complaint.cell.modelClasses.ComplaintModel;
import com.dohr.complaint.cell.modelClasses.GetRegComplaintModle;
import com.dohr.complaint.cell.modelClasses.ListofcomplaintsDemo;
import com.dohr.complaint.cell.modelClasses.RecyclerViewModle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.LOGIN_PREF;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.UserPref;

public class ComplaintStatus extends AppCompatActivity {
    String compl_detail, title, categories, status, date;
    private ArrayList<ComplaintDetail> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    Button checkstatus;
    HashMap<String, String> data_map = new HashMap<>();
    ImageView backbtn;
    SharedPreferences sharedpreferences;
    String user_ids, apiToken;
    SwipeRefreshLayout swiperefresh;
    String st_ComplaintStatus,st_DeptName,st_PersonPhoneNumber,
            st_Location,st_PersonEmail,st_PersonAddress,st_ComplaintType,st_ComplaintSubType,st_Subject,st_Detail,st_Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_status);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        backbtn = findViewById(R.id.backbtn);
        swiperefresh = findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeColors(getResources().getColor(R.color.colorOrange));
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                displayAllCompliants();
            }
        });

        swiperefresh.setRefreshing(true);

        sharedpreferences = getSharedPreferences(UserPref, Context.MODE_PRIVATE);

        user_ids = sharedpreferences.getString(UserPrefence.User_id, "no id");
        apiToken = sharedpreferences.getString(UserPrefence.Api_token, "no data");
        Log.e("user_id", "onCreate: " + user_ids);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComplaintStatus.this, Home.class));
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        displayAllCompliants();


        // prepareMovieData();


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ComplaintDetail movie = movieList.get(position);

                st_ComplaintStatus=movie.getComplaintStatus();
                st_DeptName=movie.getDeptName();
                st_PersonPhoneNumber=movie.getPersonPhoneNumber();
                st_Location=movie.getLocation();
                st_PersonEmail=movie.getPersonEmail();
               st_PersonAddress =movie.getPersonAddress();
               st_ComplaintType= movie.getComplaintType();
                st_ComplaintSubType=movie.getSubComplaintType();
                st_Subject=movie.getSubject();
                st_Detail=movie.getDetails();
               st_Image=movie.getImage();

                Log.e("full", st_ComplaintStatus+st_DeptName+ st_PersonPhoneNumber+"   "+st_Image);

                //sending data
                Intent intent=new Intent(ComplaintStatus.this,MyComplaintDetail.class);
                intent.putExtra("st_ComplaintStatus",st_ComplaintStatus);
                intent.putExtra("st_DeptName",st_DeptName);
                intent.putExtra("st_PersonPhoneNumber",st_PersonPhoneNumber);
                intent.putExtra("st_Location",st_Location);
                intent.putExtra("st_PersonEmail",st_PersonEmail);
                intent.putExtra("st_PersonAddress",st_PersonAddress);
                intent.putExtra("st_ComplaintType",st_ComplaintType);
                intent.putExtra("st_ComplaintSubType",st_ComplaintSubType);
                intent.putExtra("st_Subject",st_Subject);
                intent.putExtra("st_Detail",st_Detail);
                intent.putExtra("st_Image",st_Image);
                Log.e("st_Image", st_Image);
                startActivity(intent);




               /* Toast.makeText(getApplicationContext()," is selected!"+movie.getId().toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ComplaintStatus.this, MyComplaintDetail.class));*/
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

   /* private void prepareMovieData() {
        RecyclerViewModle  movie = new RecyclerViewModle("Complaint Against", "Approved", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.","Women Rights","26/07/2018");
        movieList.add(movie);


        RecyclerViewModle  movie1 = new RecyclerViewModle("Complaint Against", "Rejected", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,","Women Rights","26/07/2018");
        movieList.add(movie1);
        RecyclerViewModle  movie2 = new RecyclerViewModle("Complaint Against", "Pending", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,","Women Rights","26/07/2018");
        movieList.add(movie2);
        RecyclerViewModle  movie3 = new RecyclerViewModle("Complaint Against", "Pending", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,","Women Rights","26/07/2018");
        movieList.add(movie3);
        RecyclerViewModle  movie4 = new RecyclerViewModle("Complaint Against", "Pending", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,","Women Rights","26/07/2018");
        movieList.add(movie4);
        RecyclerViewModle  movie5 = new RecyclerViewModle("Complaint Against", "Rejected", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,","Women Rights","26/07/2018");
        movieList.add(movie5);
        RecyclerViewModle  movie6 = new RecyclerViewModle("Complaint Against", "Approved", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,","Women Rights","26/07/2018");
        movieList.add(movie6);
        RecyclerViewModle  movie7 = new RecyclerViewModle("Complaint Against", "Pending", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,","Women Rights","26/07/2018");
        movieList.add(movie7);
        RecyclerViewModle  movie8 = new RecyclerViewModle("Complaint Against", "Pending", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,","Women Rights","26/07/2018");
        movieList.add(movie8);
        RecyclerViewModle  movie9 = new RecyclerViewModle("Complaint Against", "Pending", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,","Women Rights","26/07/2018");
        movieList.add(movie9);
        RecyclerViewModle  movie10 = new RecyclerViewModle("Complaint Against", "Pending", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque et sollicitudin odio,","Women Rights","26/07/2018");
        movieList.add(movie10);



        recyclerViewAdapter.notifyDataSetChanged();
    }
*/

    void displayAllCompliants() {
        movieList.clear();
        data_map.put("api_token", apiToken);
        data_map.put("user_id", user_ids);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BaseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        ComplaintApi rest = retrofit.create(ComplaintApi.class);
        Call<ListofcomplaintsDemo> call = rest.getAllComplaints(data_map);
        call.enqueue(new Callback<ListofcomplaintsDemo>() {
            @Override
            public void onResponse(Call<ListofcomplaintsDemo> call, Response<ListofcomplaintsDemo> response) {


  /*****************************************************errorrrr********************************************************************************************/

/*
                    ComplaintDetail complaintDetail= response.body().getComplaintDetail();
                compl_detail=complaintDetail.getDetails();
                title=complaintDetail.getDeptName();
                categories=complaintDetail.getComplaintType();
                //status=complaintDetail.get();
                //date=complaintDetail.get();
                Log.e("ComplainDetail", complaintDetail.getDetails());*/

                if (response.isSuccessful()) {
                    Toast.makeText(ComplaintStatus.this, "get data", Toast.LENGTH_SHORT).show();
                    if (response.body().getSuccess().equals("true")){
                        ListofcomplaintsDemo data_list = response.body();
                        for(ComplaintDetail c:data_list.getComplaintDetail()) {
                            movieList.add(c);
                            recyclerViewAdapter = new RecyclerViewAdapter(movieList);
                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.notifyDataSetChanged();
                            swiperefresh.setRefreshing(false);
                            runLayoutAnimation(recyclerView);
                            String image = c.getImage();
                            Log.e("image", ""+image);

                            Log.e("dataComplaints", "onResponse: " + response.body().getComplaintDetail().toString());


                            Log.e("data", "onResponse: ");
                        }
                    }



                }
            }

            @Override
            public void onFailure(Call<ListofcomplaintsDemo> call, Throwable t) {
                Log.e("Message", "" + t.getMessage());
                Toast.makeText(ComplaintStatus.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}






