package com.app.desiaustralia.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomeDataSuccess {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("home")
    @Expose
    ArrayList<Home> homeArrayList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<Home> getHomeArrayList() {
        return homeArrayList;
    }

    public void setHomeArrayList(ArrayList<Home> homeArrayList) {
        this.homeArrayList = homeArrayList;
    }
}
