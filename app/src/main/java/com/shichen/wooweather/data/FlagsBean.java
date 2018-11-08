package com.shichen.wooweather.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public class FlagsBean {
    @SerializedName("nearest-station")
    private double neareststation;
    private String units;
    private List<String> sources;

    public double getNeareststation() {
        return neareststation;
    }

    public void setNeareststation(double neareststation) {
        this.neareststation = neareststation;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }
}
