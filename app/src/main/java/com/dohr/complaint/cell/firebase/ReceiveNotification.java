package com.dohr.complaint.cell.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.SyncStateContract;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.RoomSqliteDB.DhrDatabase;
import com.dohr.complaint.cell.javaClasses.Home;
import com.dohr.complaint.cell.javaClasses.Notifications;
import com.dohr.complaint.cell.modelClasses.Notification;
import com.dohr.complaint.cell.modelClasses.NotificationModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReceiveNotification extends FirebaseMessagingService {

    DhrDatabase databaseRooom;
    List<NotificationModel> data = new ArrayList<>();
    NotificationModel notificationModel;
    String noti_id;
    private static final String TAG = null;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String complaint_id = remoteMessage.getData().get("complaint_id");
        String user_id = remoteMessage.getData().get("user_id");
        String title = remoteMessage.getData().get("title");
        String status = remoteMessage.getData().get("status");
        String date = remoteMessage.getData().get("date");
        String body = remoteMessage.getData().get("body");
        saveToDb(complaint_id, user_id, title, status, date, body);

       /* JSONObject json ;
        try {
            json = new JSONObject(remoteMessage.getData().toString());
            handleDataMessage(json);
        } catch (JSONException e) {
            Log.e("JSONException: ", e.toString());
        }*/
    }
    private void handleDataMessage(JSONObject json) {
        JSONObject data = null;
        Log.e("working: ", "success");
        try {

            data = json.getJSONObject("complain_data");
            //String complain_id=data.getString("complain_id");
            Log.e("handleDataMessage: ",data.toString());
            String title=data.getString("complaint_id");
           // String msg=data.getString("body");
            Log.e(TAG, "title: "+title+"msg:  " );

            //saveToDb(title,msg);

            Intent intent = new Intent(getApplicationContext(), Home.class);
            intent.putExtra("key","status");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
            notificationBuilder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
            notificationBuilder.setContentTitle("DOHR Notification");
           // Spanned spanned = Html.fromHtml(msg);
            //notificationBuilder.setContentText(spanned);
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder.setSound(soundUri);
            notificationBuilder.setSmallIcon(R.drawable.logo);
            notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.logo));
            notificationBuilder.setAutoCancel(true);
            Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(1000);
            notificationBuilder.setContentIntent(pendingIntent);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void saveToDb(String complaint_id, String user_id, String title, String status, String date, String body) {
        databaseRooom = Room.databaseBuilder(getApplicationContext(),
                DhrDatabase.class, "databaseName")
                .fallbackToDestructiveMigration()
                .build();
        final int min = 20;
        final int max = 8000;
        final int id = new Random().nextInt((max - min) + 1) + min;

        notificationModel = new NotificationModel(complaint_id, user_id, title, status, date, body);
        data.add(notificationModel);
        databaseRooom.daoAccess().insertNotification(data);
        Log.e("saveToDb: ", "sucessfully");
        Log.e("data: ", data.toString());
        showNotification();
    }

    private void showNotification() {
        Intent intent = new Intent(getApplicationContext(), Notifications.class);
        intent.putExtra("key","status");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
        notificationBuilder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        notificationBuilder.setContentTitle("DOHR Notification");
        // Spanned spanned = Html.fromHtml(msg);
        //notificationBuilder.setContentText(spanned);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationBuilder.setSound(soundUri);
        notificationBuilder.setSmallIcon(R.drawable.logo);
        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.logo));
        notificationBuilder.setAutoCancel(true);
        Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(1000);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }































      /*  // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e("Recieve", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e("data payload", "Message data payload: " + remoteMessage.getData());

            if (*//* Check if data needs to be processed by long running job *//* true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
            } else {
                // Handle message within 10 seconds
//                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e("message", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

*/
    }

