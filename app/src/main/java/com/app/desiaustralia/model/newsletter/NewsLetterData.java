package com.app.desiaustralia.model.newsletter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsLetterData {

@SerializedName("dataitems")
    @Expose
    private ArrayList<DataItems> arrayList;

    public ArrayList<DataItems> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<DataItems> arrayList) {
        this.arrayList = arrayList;
    }
}
