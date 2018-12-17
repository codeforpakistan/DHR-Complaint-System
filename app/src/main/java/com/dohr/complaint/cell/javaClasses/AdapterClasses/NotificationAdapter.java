package com.dohr.complaint.cell.javaClasses.AdapterClasses;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.modelClasses.NotificationModal;

import java.util.List;
import java.util.Random;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private List<NotificationModal> notificationModalList;

    public NotificationAdapter(List<NotificationModal> notificationModalList) {
        this.notificationModalList = notificationModalList;

    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_list, parent, false);

        return new NotificationAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NotificationModal notificationModal = notificationModalList.get(position);

        holder.title.setText(notificationModal.getTitle());
        holder.date.setText(notificationModal.getDate());
        holder.notification.setText(notificationModal.getNotification());
        //notificationModal.getImage_view();
        //holder.imageView.setImageBitmap(notificationModal.getImage_view());

        /*Random random = new Random();

        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        holder.rel.setBackgroundColor(color);*/
    }

    @Override
    public int getItemCount() {
        return notificationModalList.size();
    }

    public interface ItemListener {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,date,notification;
        public ImageView imageView;
        public RelativeLayout rel;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            notification = (TextView) view.findViewById(R.id.notification);
            imageView = (ImageView) view.findViewById(R.id.image_view);
           // rel = view.findViewById(R.id.rel);


        }
    }




}
