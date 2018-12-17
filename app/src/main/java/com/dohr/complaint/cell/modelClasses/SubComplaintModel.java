package com.dohr.complaint.cell.modelClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubComplaintModel {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Sub Categories")
    @Expose
    private List<SubCategory> subCategories = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "SubComplaintModel{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }

    @Entity
    public static class SubCategory {
        @SerializedName("id")
        @Expose
        @NonNull
        @PrimaryKey
        private String id;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("cat_slug")
        @Expose
        private String catSlug;
        @SerializedName("level")
        @Expose
        private String level;
        @SerializedName("parent_id")
        @Expose
        private String parentId;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public SubCategory(String id, String catName, String parentId) {
            this.id = id;
            this.catName = catName;
            this.parentId = parentId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }

        public String getCatSlug() {
            return catSlug;
        }

        public void setCatSlug(String catSlug) {
            this.catSlug = catSlug;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @Override
        public String toString() {
            return "SubCategory{" +
                    "id='" + id + '\'' +
                    ", catName='" + catName + '\'' +
                    ", catSlug='" + catSlug + '\'' +
                    ", level='" + level + '\'' +
                    ", parentId='" + parentId + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    '}';
        }
    }



}
