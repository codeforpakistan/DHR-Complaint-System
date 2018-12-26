package com.dohr.complaint.cell.RoomSqliteDB;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import com.dohr.complaint.cell.firebase.ComplainData;
import com.dohr.complaint.cell.modelClasses.AllCategory;
import com.dohr.complaint.cell.modelClasses.ComplaintModel;
import com.dohr.complaint.cell.modelClasses.ComplaintType;
import com.dohr.complaint.cell.modelClasses.Notification;
import com.dohr.complaint.cell.modelClasses.NotificationModel;
import com.dohr.complaint.cell.modelClasses.SubComplaintModel;

@Database(entities = {ComplaintModel.AllCategory.class, SubComplaintModel.SubCategory.class, Notification.class, NotificationModel.class}, version = 2, exportSchema = false)
public abstract class DhrDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess() ;
}
