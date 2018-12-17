package com.dohr.complaint.cell.javaClasses.AdapterClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.modelClasses.Announcement;
import com.dohr.complaint.cell.modelClasses.PhoneDir;

import java.util.ArrayList;

/**
 * Created by HP on 9/25/2018.
 */

public class DirctoryAdpter  extends RecyclerView.Adapter<DirctoryAdpter.MyViewHolder> {

    private ArrayList<PhoneDir> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName,designation_txt,phone_txt;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.name_tv);
            this.designation_txt = (TextView) itemView.findViewById(R.id.designation_txt);
            this.phone_txt = (TextView) itemView.findViewById(R.id.phone_txt);

            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public DirctoryAdpter(ArrayList<PhoneDir> data) {
        this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dir_row, parent, false);

        // view.setOnClickListener(ListofComplaints.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textViewName = holder.textViewName;
        TextView phone_txt = holder.phone_txt;
        TextView designation_txt = holder.designation_txt;

        textViewName.setText(dataSet.get(position).getName());
        designation_txt.setText(dataSet.get(position).getName());
        phone_txt.setText(dataSet.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }



}

