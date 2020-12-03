package com.app.desiaustralia.model.topbollywoodsong;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BollywoodSongList {

    @SerializedName("top10bollywoodsong")
    @Expose
    private ArrayList<BollywoodSongData> data;

    public ArrayList<BollywoodSongData> getData() {
        return data;
    }

    public void setData(ArrayList<BollywoodSongData> data) {
        this.data = data;
    }
}
