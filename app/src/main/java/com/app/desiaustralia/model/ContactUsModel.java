package com.app.desiaustralia.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUsModel {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("success")
    @Expose
    private boolean success;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
