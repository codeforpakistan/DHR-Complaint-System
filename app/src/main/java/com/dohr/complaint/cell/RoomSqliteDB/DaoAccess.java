package com.dohr.complaint.cell.RoomSqliteDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.dohr.complaint.cell.firebase.ComplainData;
import com.dohr.complaint.cell.modelClasses.AllCategory;
import com.dohr.complaint.cell.modelClasses.ComplaintModel;
import com.dohr.complaint.cell.modelClasses.Notification;
import com.dohr.complaint.cell.modelClasses.NotificationModel;
import com.dohr.complaint.cell.modelClasses.SubComplaintModel;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DaoAccess {
    @Insert
    void insertComplaintList(List<ComplaintModel.AllCategory> allCategories);
    @Insert
    void insertSubComplaintList(List<SubComplaintModel.SubCategory> subCategories);

    @Query("SELECT * FROM AllCategory")
    List<ComplaintModel.AllCategory> fetchComplaintList();

    @Query("SELECT * FROM SubCategory")
    List<SubComplaintModel.SubCategory> fetchSubComplaintList();

    @Query("SELECT * FROM SubCategory WHERE parentId = :id")
    List<SubComplaintModel.SubCategory> fetchSubComplaintList(String id);


    @Query("DELETE FROM AllCategory")
    void deleteCategory();
    @Query("DELETE FROM SubCategory")
    void deleteSubCategory();


    ////////////////////////////////////////////////////////////////////////////////////////////
    //database

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNotification(List<NotificationModel> notificationList);

    @Query("SELECT * FROM NotificationModel")
    List<NotificationModel> getAllNoficitions();

   /* @Query("DELETE FROM ComplainData WHERE notificationId = :userId")
    void deleteNotification(int userId);
*/
}
