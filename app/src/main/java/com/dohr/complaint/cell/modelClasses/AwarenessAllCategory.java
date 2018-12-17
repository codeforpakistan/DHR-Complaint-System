package com.dohr.complaint.cell.modelClasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import java.util.List;

public class AwarenessAllCategory implements Parcelable {


    @Json(name = "id")
    private String id;
    @Json(name = "title")
    private String title;
    @Json(name = "image")
    private String image;
    @Json(name = "description")
    private String description;
    @Json(name = "created_at")
    private Object createdAt;
    @Json(name = "updated_at")
    private Object updatedAt;

    public AwarenessAllCategory(String id, String title, String image, String description, Object createdAt, Object updatedAt) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected AwarenessAllCategory(Parcel in) {
        id = in.readString();
        title = in.readString();
        image = in.readString();
        description = in.readString();
    }

    public static final Creator<AwarenessAllCategory> CREATOR = new Creator<AwarenessAllCategory>() {
        @Override
        public AwarenessAllCategory createFromParcel(Parcel in) {
            return new AwarenessAllCategory(in);
        }

        @Override
        public AwarenessAllCategory[] newArray(int size) {
            return new AwarenessAllCategory[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "AwarenessAllCategory{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(description);
    }
}