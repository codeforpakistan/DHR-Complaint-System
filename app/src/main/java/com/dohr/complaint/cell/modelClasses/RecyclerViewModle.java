package com.dohr.complaint.cell.modelClasses;

public class RecyclerViewModle {
    private String compl_detail,title, categories,status,date;

    public RecyclerViewModle(String compl_detail, String title, String categories, String status, String date) {
        this.compl_detail = compl_detail;
        this.categories = categories;
        this.status = status;
        this.title = title;
        this.date = date;
    }

    public String getCompl_detail() {
        return compl_detail;
    }

    public void setCompl_detail(String compl_detail) {
        this.compl_detail = compl_detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "RecyclerViewModle{" +
                "compl_detail='" + compl_detail + '\'' +
                ", title='" + title + '\'' +
                ", categories='" + categories + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}