package com.dohr.complaint.cell.javaClasses.Awareness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.UtilsClasses.UserPrefence;
import com.dohr.complaint.cell.interfaceClasses.LoginAPI;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.AwarenessAdapter;
import com.dohr.complaint.cell.javaClasses.Home;
import com.dohr.complaint.cell.modelClasses.AllCategory;

import com.dohr.complaint.cell.modelClasses.AwarenessAllCategory;
import com.dohr.complaint.cell.modelClasses.AwarenessModel;
import com.dohr.complaint.cell.modelClasses.ComplaintModel;
import com.dohr.complaint.cell.modelClasses.DataModel;
import com.dohr.complaint.cell.modelClasses.SubComplaintModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.UserPref;

public class Awareness extends AppCompatActivity{

    public static final String BaseUrl = "http://banatapp.com/HumanRights/categories/";
    RecyclerView recyclerView;
    ArrayList arrayList;
    CardView cardview;
    String success;
    ImageView backbtn;
    HashMap<String, String> map = new HashMap<>();
    String apiToken;
    SwipeRefreshLayout swiperefresh;
    List<AwarenessAllCategory> allCategories=new ArrayList<>();
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);
        sharedpreferences = getSharedPreferences(UserPref, Context.MODE_PRIVATE);
        apiToken=sharedpreferences.getString(UserPrefence.Api_token,"no data");
        map.put("api_token",apiToken);

        swiperefresh = findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeColors(getResources().getColor(R.color.colorOrange));
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postData();
            }
        });

        swiperefresh.setRefreshing(true);

        //cardview = findViewById(R.id.cardview);
        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Awareness.this, Home.class));
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                postData();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(Awareness.this,AwarenessDetails.class);
                intent.putExtra("allCat",new AwarenessAllCategory(allCategories.get(position).getId(),allCategories.get(position).getTitle(),allCategories.get(position).getImage(),allCategories.get(position).getDescription(),allCategories.get(position).getCreatedAt(),allCategories.get(position).getUpdatedAt()));
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    void postData()
    {
        allCategories.clear();
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
        Call<AwarenessModel> call =  rest.getAllAwareness(map);
        call.enqueue(new Callback<AwarenessModel>() {
            @Override
            public void onResponse(Call<AwarenessModel> call, Response<AwarenessModel> response) {
                success = response.body().getSuccess();
                Log.e("awar", "onResponse: "+success );
                if (response.isSuccessful()) {
                    /* if (success.equals("true")) {*/
                    for(AwarenessAllCategory d:response.body().getAllCategories()) {
                        allCategories.add(d);
                        //AwarenessAdapter adapter = new AwarenessAdapter(getBaseContext(), allCategories);

                        Rec_Adpt rec_adpt = new Rec_Adpt(allCategories,getBaseContext());
                        recyclerView.setAdapter(rec_adpt);

                        swiperefresh.setRefreshing(false);
                        runLayoutAnimation(recyclerView);

                        String CatName = allCategories.get(0).getTitle();
                        String CatImage = allCategories.get(0).getImage();
                        Log.e("CatName", CatName);
                        Log.e("CatImage", CatImage);
                    }

                }
            }

            @Override
            public void onFailure(Call<AwarenessModel> call, Throwable t) {

                Log.e("Message", ""+t.getMessage());
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