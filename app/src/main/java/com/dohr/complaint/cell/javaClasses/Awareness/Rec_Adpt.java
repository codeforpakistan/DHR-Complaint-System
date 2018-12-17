package com.dohr.complaint.cell.javaClasses.Awareness;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.modelClasses.AwarenessAllCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.dohr.complaint.cell.UtilsClasses.Config.BaseUrl;

/**
 * Created by Sumaira on 9/24/2018.
 */

public class Rec_Adpt extends RecyclerView.Adapter<Rec_Adpt.MyViewHolder>  {



    private List<AwarenessAllCategory> dataSet;
    Context m_coContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imagethumb);
        }
    }

    public Rec_Adpt(List<AwarenessAllCategory> data, Context context) {
        this.dataSet = data;
        this.m_coContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.awareness_recycler_item, parent, false);

        // view.setOnClickListener(ListofComplaints.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        textViewName.setText(dataSet.get(listPosition).getTitle());

        Picasso.with(m_coContext).load(BaseUrl+dataSet.get(listPosition).getImage()).resize(300,300).into(holder.imageView);
        Log.e("image", "onBindViewHolder: "+BaseUrl+dataSet.get(listPosition).getImage() );


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }



}
