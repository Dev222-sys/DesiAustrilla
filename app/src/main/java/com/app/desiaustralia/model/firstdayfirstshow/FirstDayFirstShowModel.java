package com.app.desiaustralia.model.firstdayfirstshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FirstDayFirstShowModel {
    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("firstdayshow")
    @Expose
    private ArrayList<FirstDayData> data;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<FirstDayData> getData() {
        return data;
    }

    public void setData(ArrayList<FirstDayData> data) {
        this.data = data;
    }
}

