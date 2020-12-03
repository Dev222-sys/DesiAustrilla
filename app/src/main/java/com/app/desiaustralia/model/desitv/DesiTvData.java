package com.app.desiaustralia.model.desitv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DesiTvData {

    @SerializedName("latest")
    @Expose
    private ArrayList<LatestData> latest;

    @SerializedName("trending")
    @Expose
    private ArrayList<TrendingData> trending;

    public ArrayList<LatestData> getLatest() {
        return latest;
    }

    public void setLatest(ArrayList<LatestData> latest) {
        this.latest = latest;
    }

    public ArrayList<TrendingData> getTrending() {
        return trending;
    }

    public void setTrending(ArrayList<TrendingData> trending) {
        this.trending = trending;
    }
}
