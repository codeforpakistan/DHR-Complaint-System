package com.dohr.complaint.cell.firebase;

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
import com.dohr.complaint.cell.modelClasses.Notification;
import com.dohr.complaint.cell.modelClasses.NotificationModel;

import java.util.List;
import java.util.Random;

/**
 * Created by Sumaira on 10/16/2018.
 */

public class OfflineAdpter extends RecyclerView.Adapter<OfflineAdpter.MyViewHolder> {


    private List<NotificationModel> dataSet;
    ColorGenerator  generator = ColorGenerator.MATERIAL;
    public OfflineAdpter(List<NotificationModel> data) {
        this.dataSet = data;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        /*TextView textViewName;
        TextView textViewVersion;
        TextView textViewCat;
        TextView dateTime;
        private View rel;
        TextView textViewdetail;*/
        // ImageView imageViewIcon;

        TextView title;
        TextView date;
        TextView details;
        TextView status;
        ImageView image_view;


        public MyViewHolder(View itemView) {
            super(itemView);
            //this.textViewName = (TextView) itemView.findViewById(R.id.textcomplaintTitle);
           /* this.textViewVersion = (TextView) itemView.findViewById(R.id.textStatus);
            this.textViewCat=(TextView)itemView.findViewById(R.id.CatStatus);
            this.rel = itemView.findViewById(R.id.rel);
            this.dateTime=itemView.findViewById(R.id.datetime);*/
            this.image_view = itemView.findViewById(R.id.image_view);
            this.title = itemView.findViewById(R.id.title);
            this.date = itemView.findViewById(R.id.date);
            this.details = itemView.findViewById(R.id.details);
            this.status = itemView.findViewById(R.id.status);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_list, parent, false);

        // view.setOnClickListener(ListofComplaints.myOnClickListener);

        OfflineAdpter.MyViewHolder myViewHolder = new OfflineAdpter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int listPosition) {
        //TextView textViewName = holder.textViewName;
        TextView title = holder.title;
        TextView date=holder.date;
        TextView details=holder.details;
        TextView status=holder.status;
        Random random = new Random();

        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        title.setText(dataSet.get(listPosition).getTitle());
        date.setText(dataSet.get(listPosition).getDate());
        details.setText(dataSet.get(listPosition).getBody());
        status.setText(dataSet.get(listPosition).getStatus());


        TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(dataSet.get(listPosition).getTitle().charAt(0)), generator.getRandomColor());

        holder.image_view.setImageDrawable(drawable);

       // textViewdetail.setText((CharSequence) dataSet.get(listPosition).getMsg());
        //textViewCat.setBackgroundColor(color);

    }

    @Override
    public  int getItemCount() {
        return dataSet.size();
    }

    public  void removeItem(int position) {
        dataSet.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }
}
