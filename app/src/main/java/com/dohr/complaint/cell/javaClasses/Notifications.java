package com.dohr.complaint.cell.javaClasses;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.RoomSqliteDB.DhrDatabase;
import com.dohr.complaint.cell.firebase.ComplainData;
import com.dohr.complaint.cell.firebase.OfflineAdpter;
import com.dohr.complaint.cell.firebase.RecyclerItemTouchHelper;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.NotificationAdapter;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.RecyclerViewAdapter;
import com.dohr.complaint.cell.modelClasses.Notification;
import com.dohr.complaint.cell.modelClasses.NotificationModal;
import com.dohr.complaint.cell.modelClasses.RecyclerViewModle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Notifications extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    HashMap<String, String> map = new HashMap<>();
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    List<Notification> data;
    DhrDatabase databaseRooom;
    ImageView no_data_img,backbtn,no_notification;
    SwipeRefreshLayout swiperefresh;
    String complaint_id, user_id,title,body,status,date;
    /*String compl_detali,title,categories,status,date;
    private List<NotificationModal> notificationlist = new ArrayList<>();
    private RecyclerView recycler_view;
    NotificationAdapter notificationAdapter;
    ImageView backbtn;

    TextView ComplaintType,subcomplaint,subject,details;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        no_data_img = findViewById(R.id.no_data_img);
        swiperefresh = findViewById(R.id.swiperefresh);
        backbtn = findViewById(R.id.backbtn);
        no_notification = findViewById(R.id.no_notification);
        swiperefresh.setRefreshing(true);
        swiperefresh.setColorSchemeColors(getResources().getColor(R.color.colorOrange));


        //receive message from fcm in background
        if (getIntent().getExtras() != null) {



            complaint_id = getIntent().getStringExtra("complaint_id");
            user_id = getIntent().getStringExtra("user_id");
            title = getIntent().getStringExtra("title");
            body = getIntent().getStringExtra("body");
            status = getIntent().getStringExtra("status");
            date = getIntent().getStringExtra("date");


            Log.e("notification",complaint_id +""+user_id+""+title+""+body+""+status+""+date);

        } else {
            Toast.makeText(this, "EMPTy", Toast.LENGTH_SHORT).show();
        }


        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               //
                //displayAllCompliants();


                //swiperefresh.setRefreshing(false);
            }
        });

        //swiperefresh.setRefreshing(true);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifications.this,Home.class));
            }
        });

      /*  subcomplaint = findViewById(R.id.subcomplaint);
        subject = findViewById(R.id.subject);
        ComplaintType = findViewById(R.id.ComplaintType);
        backbtn = findViewById(R.id.backbtn);
        recycler_view = findViewById(R.id.recycler_view);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifications.this, Home.class));
            }
        });

        notificationAdapter = new NotificationAdapter(notificationlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(notificationAdapter);



        TextDrawable drawable1 = TextDrawable.builder()
                .buildRoundRect("A", Color.RED, 10); // radius in px

        TextDrawable drawable2 = TextDrawable.builder()
                .buildRound("A", Color.RED);*/
        databaseRooom = Room.databaseBuilder(getApplicationContext(),DhrDatabase.class, "databaseName").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        data = new ArrayList<Notification>();

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data.clear();
        data=databaseRooom.daoAccess().getAllNoficitions();
        adapter = new OfflineAdpter(data);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //adapter.setRefreshing(false);
        // Setup onItemTouchHandler to enable drag and drop , swipe left or right
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0,  ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
// attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //swiperefresh.setRefreshing(false);
                data.clear();
                data=databaseRooom.daoAccess().getAllNoficitions();
                Log.e( "onRefresh: ",data.toString() );
                adapter = new OfflineAdpter(data);
                recyclerView.setAdapter(adapter);
                swiperefresh.setRefreshing(false);
            }

        });



/*
        SwipeHelper swipeHelper = new SwipeHelper(getActivity(), recyclerView) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton("Delete", 0, Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Log.e( "onClick: ",""+pos );
                                // TODO:


                            }
                        }
                ));

            }
        };*/

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        //int id = data.get(viewHolder.getAdapterPosition()).getNotificationId();
        //Log.e("onSwiped: ",data.get(0).getNotificationId().toString());
        //Log.e("onSwiped: ", String.valueOf(id));
        //problem is here check it
        //adapter.removeItem(viewHolder.getAdapterPosition());
        try {
           // databaseRooom.daoAccess().deleteNotification(id);
            //data.clear();
            prepareData();
            //adapter.notifyDataSetChanged();

        }catch (Exception e){
            Log.e("Exception", e.toString());
        }

    }

    private void prepareData() {
        data.clear();
        data=databaseRooom.daoAccess().getAllNoficitions();
        adapter = new OfflineAdpter(data);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }




}