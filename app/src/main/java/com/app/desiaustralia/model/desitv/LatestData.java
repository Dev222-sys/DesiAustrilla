package com.app.desiaustralia.model.desitv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestData {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("video")
    @Expose
    private String video;

    @SerializedName("desc")
    @Expose
    private String desc;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
