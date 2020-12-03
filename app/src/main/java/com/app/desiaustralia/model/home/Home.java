package com.app.desiaustralia.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Home {

    @SerializedName("topnews")
    @Expose
    private ArrayList<TopNews> arrayList;

    public ArrayList<TopNews> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<TopNews> arrayList) {
        this.arrayList = arrayList;
    }
}
