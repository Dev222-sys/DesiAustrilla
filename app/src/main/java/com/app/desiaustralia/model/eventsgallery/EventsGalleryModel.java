package com.app.desiaustralia.model.eventsgallery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventsGalleryModel {
    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("events")
    @Expose
    private ArrayList<GalleryData> data;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<GalleryData> getData() {
        return data;
    }

    public void setData(ArrayList<GalleryData> data) {
        this.data = data;
    }
}

