package com.app.desiaustralia.model.newsletter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsLetterModel {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("data")
    @Expose
    private ArrayList<NewsLetterData> data;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<NewsLetterData> getData() {
        return data;
    }

    public void setData(ArrayList<NewsLetterData> data) {
        this.data = data;
    }
}
