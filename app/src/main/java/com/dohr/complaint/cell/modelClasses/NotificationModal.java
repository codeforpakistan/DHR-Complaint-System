package com.dohr.complaint.cell.modelClasses;



public class NotificationModal {
    private String title,date,notification;
    int image_view;

    public NotificationModal(String title,  String date, String notification, int image_view) {
        this.title = title;
        this.date = date;
        this.notification = notification;
        this.image_view = image_view;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public int getImage_view() {
        return image_view;
    }

    public void setImage_view(int image_view) {
        this.image_view = image_view;
    }
}