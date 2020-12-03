package com.app.desiaustralia.model.topbollywoodsong;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TopBollywoodSongModel {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("bollywoodsong")
    @Expose
    private ArrayList<BollywoodSongList> data;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<BollywoodSongList> getData() {
        return data;
    }

    public void setData(ArrayList<BollywoodSongList> data) {
        this.data = data;
    }
}
