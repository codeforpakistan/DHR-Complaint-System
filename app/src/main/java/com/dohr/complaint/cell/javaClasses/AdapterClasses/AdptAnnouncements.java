package com.dohr.complaint.cell.javaClasses.AdapterClasses;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.modelClasses.Announcement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by HP on 9/24/2018.
 */

public class AdptAnnouncements  extends RecyclerView.Adapter<AdptAnnouncements.MyViewHolder> {

    private ArrayList<Announcement> dataSet;

    public String letter;
    ColorGenerator generator = ColorGenerator.MATERIAL;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName,announce_desc,date_announce;
        //TextView title;
        ImageView letter;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.title_txt);
            this.announce_desc = (TextView) itemView.findViewById(R.id.announce_desc);
            this.date_announce = (TextView) itemView.findViewById(R.id.date_announce);

            this.letter = (ImageView) itemView.findViewById(R.id.image_view);
            //title = (TextView) itemView.findViewById(R.id.gmailitem_title);

            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public AdptAnnouncements(ArrayList<Announcement> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.announcements_list, parent, false);

        // view.setOnClickListener(ListofComplaints.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        //holder.title.setText((CharSequence) dataSet.get(listPosition));
        letter = String.valueOf(dataSet.get(listPosition).getTitle().charAt(0));

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letter, generator.getRandomColor());

        holder.letter.setImageDrawable(drawable);

        TextView textViewName = holder.textViewName;
        TextView announce_desc = holder.announce_desc;
        TextView date_announce = holder.date_announce;

        textViewName.setText(dataSet.get(listPosition).getTitle());
        announce_desc.setText(dataSet.get(listPosition).getDescription());
        Log.e("onBindViewHolder: ",  dataSet.get(listPosition).getCreatedAt());
       /* SimpleDateFormat format = new SimpleDateFormat("dd/MMM/YYYY");
        String date = format.format(dataSet.get(listPosition).getCreatedAt());
        date_announce.setText(date);*/


    }


    @Override
    public int getItemCount()
    {
        //return dataSet == null ? 0 : dataSet.size();
        return dataSet.size();
    }



}
