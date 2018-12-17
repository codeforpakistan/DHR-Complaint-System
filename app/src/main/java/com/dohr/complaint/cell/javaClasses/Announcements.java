package com.dohr.complaint.cell.javaClasses;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.amulyakhare.textdrawable.TextDrawable;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.UtilsClasses.UserPrefence;
import com.dohr.complaint.cell.interfaceClasses.LoginAPI;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.AdptAnnouncements;
import com.dohr.complaint.cell.modelClasses.AllCategory;
import com.dohr.complaint.cell.modelClasses.Announcement;
import com.dohr.complaint.cell.modelClasses.AnnouncementModule;
import com.dohr.complaint.cell.modelClasses.AwarenessModel;

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

public class Announcements extends AppCompatActivity {
    public static final String BaseUrl = "https://banatapp.com/HumanRights/";
    ImageView backbtn;
    String success;
    String apiToken;
    SharedPreferences sharedpreferences;
    HashMap<String, String> map = new HashMap<>();
    RecyclerView recyclerView;
    ArrayList<Announcement> data=new ArrayList<>();
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout anounceswiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        backbtn = findViewById(R.id.backbtn);
        anounceswiperefresh = findViewById(R.id.anounceswiperefresh);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        sharedpreferences = getSharedPreferences(UserPref, Context.MODE_PRIVATE);
        apiToken=sharedpreferences.getString(UserPrefence.Api_token,"no data");
        map.put("api_token",apiToken);
        anounceswiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postData();
            }
        });
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
                startActivity(new Intent(Announcements.this, Home.class));
            }
        });
        //internet connection checking
        postData();
    }

    void postData()
    {
        anounceswiperefresh.setRefreshing(true);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(MoshiConverterFactory.create()).client(okHttpClient)
                .build();
        LoginAPI rest = retrofit.create(LoginAPI.class);
        Call<AnnouncementModule> call =  rest.getAllAnnouncement(map);

        call.enqueue(new Callback<AnnouncementModule>() {
            @Override
            public void onResponse(Call<AnnouncementModule> call, final Response<AnnouncementModule> response) {
                success = response.body().getSuccess();
                Log.e("announcements", "onResponse: "+success );
                if (response.isSuccessful()) {
                    /* if (success.equals("true")) {*/
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            data.clear();
                            anounceswiperefresh.setRefreshing(false);

                            for(Announcement d:response.body().getAnnouncements()) {
                                data.add(d);
                                adapter = new AdptAnnouncements(data);
                                Log.e("announcements", "onResponse: "+d.getTitle()+""+response.body().getAnnouncements());

                            }
                            recyclerView.setAdapter(adapter);
                            runLayoutAnimation(recyclerView);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AnnouncementModule> call, Throwable t) {
                Log.e("Message", ""+t.getMessage());
                anounceswiperefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        postData();
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
