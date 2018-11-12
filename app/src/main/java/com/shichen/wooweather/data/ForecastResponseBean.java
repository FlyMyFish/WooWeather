package com.shichen.wooweather.data;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/12.
 */
public class ForecastResponseBean {

    private String latitude;
    private String longitude;
    private String timezone;
    private CurrentlyBean currently;
    private HourlyBean hourly;
    private DailyBean daily;
    private FlagsBean flags;
    private int offset;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public CurrentlyBean getCurrently() {
        return currently;
    }

    public void setCurrently(CurrentlyBean currently) {
        this.currently = currently;
    }

    public HourlyBean getHourly() {
        return hourly;
    }

    public void setHourly(HourlyBean hourly) {
        this.hourly = hourly;
    }

    public DailyBean getDaily() {
        return daily;
    }

    public void setDaily(DailyBean daily) {
        this.daily = daily;
    }

    public FlagsBean getFlags() {
        return flags;
    }

    public void setFlags(FlagsBean flags) {
        this.flags = flags;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
