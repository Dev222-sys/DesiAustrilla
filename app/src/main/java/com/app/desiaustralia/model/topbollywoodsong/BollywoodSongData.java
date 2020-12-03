package com.app.desiaustralia.model.topbollywoodsong;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BollywoodSongData {

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("movies_name")
    @Expose
    private String movies_name;

    @SerializedName("singer")
    @Expose
    private String singer;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovies_name() {
        return movies_name;
    }

    public void setMovies_name(String movies_name) {
        this.movies_name = movies_name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
