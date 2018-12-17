package com.dohr.complaint.cell.modelClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubComplaintModel2 {
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
    private List<SubCategory2> subCategories = null;

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

    public List<SubCategory2> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory2> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "SubComplaintModel2{" +
                "success='" + success + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }

    @Entity
    public static class SubCategory2 {
        @NonNull
        @PrimaryKey
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("cat_name")
        @Expose
        private String catName;
        @SerializedName("cat_slug")
        @Expose
        private String catSlug;
        @SerializedName("level")
        @Expose
        private Integer level;
        @SerializedName("parent_id")
        @Expose
        private Integer parentId;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public SubCategory2(@NonNull Integer id, String catName, Integer parentId) {
            this.id = id;
            this.catName = catName;
            this.parentId = parentId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
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
            return "SubCategory2{" +
                    "id=" + id +
                    ", catName='" + catName + '\'' +
                    ", catSlug='" + catSlug + '\'' +
                    ", level=" + level +
                    ", parentId=" + parentId +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    '}';
        }
    }

}
//-----------------------------------com.example.SubCategory.java-----------------------------------



