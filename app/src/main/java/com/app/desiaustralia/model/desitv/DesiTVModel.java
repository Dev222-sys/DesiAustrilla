package com.app.desiaustralia.model.desitv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DesiTVModel {
    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("desitv")
    @Expose
    ArrayList<DesiTvData> desitvArrayList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<DesiTvData> getDesitvArrayList() {
        return desitvArrayList;
    }

    public void setDesitvArrayList(ArrayList<DesiTvData> desitvArrayList) {
        this.desitvArrayList = desitvArrayList;
    }
}
