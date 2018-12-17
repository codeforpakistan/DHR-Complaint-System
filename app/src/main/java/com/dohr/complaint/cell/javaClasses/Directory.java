package com.dohr.complaint.cell.javaClasses;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.UtilsClasses.UserPrefence;
import com.dohr.complaint.cell.interfaceClasses.LoginAPI;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.DirctoryAdpter;
import com.dohr.complaint.cell.modelClasses.Announcement;
import com.dohr.complaint.cell.modelClasses.AnnouncementModule;
import com.dohr.complaint.cell.modelClasses.DirctoryModule;
import com.dohr.complaint.cell.modelClasses.PhoneDir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.UserPref;

public class Directory extends AppCompatActivity {
    public static final String BaseUrl = "https://banatapp.com/HumanRights/";
    ImageView backbtn;
    String success;
    String apiToken;
    SharedPreferences sharedpreferences;
    HashMap<String, String> map = new HashMap<>();
    RecyclerView recyclerView;
    ArrayList<PhoneDir> data=new ArrayList<>();
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        backbtn = findViewById(R.id.backbtn);

        swiperefresh = findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeColors(getResources().getColor(R.color.colorOrange));
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postData();
            }
        });

        swiperefresh.setRefreshing(true);


        recyclerView = (RecyclerView) findViewById(R.id.recy_dir);
        sharedpreferences = getSharedPreferences(UserPref, Context.MODE_PRIVATE);
        apiToken=sharedpreferences.getString(UserPrefence.Api_token,"no data");
        map.put("api_token",apiToken);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                postData();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Directory.this, ContactUs.class));
            }
        });
    }

    void postData()
    {
        data.clear();
        //setting time out of a network
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build();

        //retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(MoshiConverterFactory.create()).client(okHttpClient)
                .build();
        LoginAPI rest = retrofit.create(LoginAPI.class);
        Call<DirctoryModule> call =  rest.getAllDirctories(map);
        call.enqueue(new Callback<DirctoryModule>() {
            @Override
            public void onResponse(Call<DirctoryModule> call, Response<DirctoryModule> response) {
                success = response.body().getSuccess();
                Log.e("directory", "onResponse: "+success );
                if (response.isSuccessful()) {
                    /* if (success.equals("true")) {*/
                    for(PhoneDir p:response.body().getPhoneDir()) {
                        data.add(p);
                        adapter = new DirctoryAdpter(data);
                        recyclerView.setAdapter(adapter);
                        swiperefresh.setRefreshing(false);
                        runLayoutAnimation(recyclerView);
                        Log.e("directory", "onResponse: "+p.getName()+""+response.body().getPhoneDir());
                    }
                }
            }

            @Override
            public void onFailure(Call<DirctoryModule> call, Throwable t) {

                Log.e("Something's went wrong", ""+t.getMessage());
            }
        });

    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
