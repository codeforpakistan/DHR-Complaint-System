package com.dohr.complaint.cell.modelClasses;


import com.squareup.moshi.Json;

    public class UserRegModel {

        @Json(name = "success")
        private String success;
        @Json(name = "status")
        private Integer status;
        @Json(name = "message")
        private String message;

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

        @Override
        public String toString() {
            return "UserRegModel{" +
                    "success='" + success + '\'' +
                    ", status=" + status +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
