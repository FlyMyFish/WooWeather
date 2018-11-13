package com.shichen.wooweather.data;

import com.shichen.wooweather.utils.DoubleUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public class HourlyBean {
    private String summary;
    private String icon;
    private List<DataBean> data;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        public String displayTime() {
            long temp = (long) time * 1000;
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
            return sdf.format(temp);
        }

        public String displayTemperature() {
            return DoubleUtils.removePointUp(temperature) + "℃";
        }

        public String displayWindSpeed() {
            return windSpeed + "m/s";
        }

        public String displayHumidity() {
            return DoubleUtils.removePointUp(humidity * 100) + "%";
        }

        public String displayVisibility() {
            return DoubleUtils.removePointUp(visibility) + "km";
        }

        public String displayCloudCover() {
            return DoubleUtils.removePointUp(cloudCover * 100) + "%";
        }

        public String displayUV(){
            if (uvIndex<=2){
                return "低";
            }else if (uvIndex<=5){
                return "中等";
            }else if (uvIndex<=7){
                return "高";
            }else if (uvIndex<=10){
                return "很高";
            }else {
                return "极高";
            }
        }

        public int getIconResId() {
            return CurrentlyBean.parseIcon(icon);
        }

        private int time;
        private String summary;
        private String icon;
        private double precipIntensity;
        private double precipProbability;
        private double temperature;
        private double apparentTemperature;
        private double dewPoint;
        private double humidity;
        private double pressure;
        private double windSpeed;
        private double windGust;
        private int windBearing;
        private double cloudCover;
        //紫外线指数:0-2:低;3-5:中等;6-7:高;8-10:很高;>=11:极高;
        private int uvIndex;
        private double visibility;
        private double ozone;
        private String precipType;
        private String precipAccumulation;

        public String getPrecipAccumulation() {
            return precipAccumulation;
        }

        public void setPrecipAccumulation(String precipAccumulation) {
            this.precipAccumulation = precipAccumulation;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public double getPrecipIntensity() {
            return precipIntensity;
        }

        public void setPrecipIntensity(double precipIntensity) {
            this.precipIntensity = precipIntensity;
        }

        public double getPrecipProbability() {
            return precipProbability;
        }

        public void setPrecipProbability(double precipProbability) {
            this.precipProbability = precipProbability;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getApparentTemperature() {
            return apparentTemperature;
        }

        public void setApparentTemperature(double apparentTemperature) {
            this.apparentTemperature = apparentTemperature;
        }

        public double getDewPoint() {
            return dewPoint;
        }

        public void setDewPoint(double dewPoint) {
            this.dewPoint = dewPoint;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
        }

        public double getWindGust() {
            return windGust;
        }

        public void setWindGust(double windGust) {
            this.windGust = windGust;
        }

        public int getWindBearing() {
            return windBearing;
        }

        public void setWindBearing(int windBearing) {
            this.windBearing = windBearing;
        }

        public double getCloudCover() {
            return cloudCover;
        }

        public void setCloudCover(double cloudCover) {
            this.cloudCover = cloudCover;
        }

        public int getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(int uvIndex) {
            this.uvIndex = uvIndex;
        }

        public double getVisibility() {
            return visibility;
        }

        public void setVisibility(double visibility) {
            this.visibility = visibility;
        }

        public double getOzone() {
            return ozone;
        }

        public void setOzone(double ozone) {
            this.ozone = ozone;
        }

        public String getPrecipType() {
            return precipType;
        }

        public void setPrecipType(String precipType) {
            this.precipType = precipType;
        }
    }
}
