package com.app.desiaustralia.model.firstdayfirstshow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.app.desiaustralia.model.newsletter.DataItems;

import java.util.ArrayList;

public class FirstDayData {

    @SerializedName("firstdayfirstshow")
    @Expose
    private ArrayList<DataItems> arrayList;

    public ArrayList<DataItems> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<DataItems> arrayList) {
        this.arrayList = arrayList;
    }
}
