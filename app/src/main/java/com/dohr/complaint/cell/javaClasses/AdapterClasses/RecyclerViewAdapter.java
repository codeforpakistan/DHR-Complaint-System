package com.dohr.complaint.cell.javaClasses.AdapterClasses;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.modelClasses.ComplaintDetail;
import com.dohr.complaint.cell.modelClasses.RecyclerViewModle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

       private ArrayList<ComplaintDetail> complaintList;

    public RecyclerViewAdapter(ArrayList<ComplaintDetail> complaintList) {
        this.complaintList = complaintList;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,status,compl_detail,categories,date;
        public RelativeLayout rel;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            status = (TextView) view.findViewById(R.id.status);
            compl_detail = (TextView) view.findViewById(R.id.compl_detail);
            categories = (TextView) view.findViewById(R.id.categories);
            date = (TextView) view.findViewById(R.id.date);
            rel = view.findViewById(R.id.rel);


        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ComplaintDetail movie = complaintList.get(position);

        holder.title.setText(movie.getDeptName());
        holder.status.setText(movie.getComplaintStatus());
        holder.compl_detail.setText(movie.getDetails());
        holder.categories.setText(movie.getSubComplaintType());
        holder.date.setText(movie.getCreatedAt());
        Random random = new Random();

//for random colors
       // int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

        //
        holder.rel.setBackgroundColor(Color.parseColor("#f7b531"));

/*
        String bk = movie.getComplaintStatus();
        if (bk.equalsIgnoreCase("Resolved")) {
            holder.rel.setBackgroundColor(Color.parseColor("#30b2e2"));
        } else if (bk.equalsIgnoreCase("Recieved")) {
            holder.rel.setBackgroundColor(Color.parseColor("#3cd559"));
        }
        else
        {
            holder.rel.setBackgroundColor(Color.parseColor("#f7b531"));

        }*/

    }


    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    public interface ItemListener {
    }

    }



    ////////////////////////////////////////////////




