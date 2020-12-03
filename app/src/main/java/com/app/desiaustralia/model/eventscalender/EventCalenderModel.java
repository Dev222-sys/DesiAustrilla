package com.app.desiaustralia.model.eventscalender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventCalenderModel {

        @SerializedName("status")
        @Expose
        private Integer status;

        @SerializedName("events")
        @Expose
        private ArrayList<CalenderData> data;


        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public ArrayList<CalenderData> getData() {
            return data;
        }

        public void setData(ArrayList<CalenderData> data) {
            this.data = data;
        }
    }


