package com.shichen.wooweather.data;

import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public class DailyBean {
    //概览
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

        private int time;
        //概览
        private String summary;
        private String icon;
        //太阳升起时间
        private int sunriseTime;
        //太阳落山时间
        private int sunsetTime;
        //0-新月 0.25-上弦月 0.5-满月 0.75-下弦月
        private double moonPhase;
        //降水量
        private double precipIntensity;
        //降水量的分布
        private String precipIntensityError;
        //一天中的最高降水量
        private double precipIntensityMax;
        //一天中最高降水量的时间
        private int precipIntensityMaxTime;
        //降水发生的概率 0-1
        private double precipProbability;
        //rain-雨 snow-雪 sleet-雨夹雪
        private String precipType;
        //气温最高温
        private double temperatureHigh;
        //气温最高的时间
        private int temperatureHighTime;
        //气温最低温
        private double temperatureLow;
        //最低温的时间
        private int temperatureLowTime;
        //体感温度，当天最高
        private double apparentTemperatureHigh;
        //体感温度，当天最高时的时间
        private int apparentTemperatureHighTime;
        //体感温度，当天最低
        private double apparentTemperatureLow;
        //体感温度，当天最低时的时间
        private int apparentTemperatureLowTime;
        //露珠
        private double dewPoint;
        //空气湿度 between 0 and 1
        private double humidity;
        //海平面空气压力 单位:毫巴
        private double pressure;
        //风速为每小时英里
        private double windSpeed;
        //风速每小时英里数
        private double windGust;
        //风速最大时，一天中的时间
        private int windGustTime;
        //风向 0°是正北方，顺时针前进（如果风速为零，则此值将不被定义。）
        private int windBearing;
        //The percentage of sky occluded by clouds, between 0 and 1, inclusive.
        private double cloudCover;
        //紫外线指数
        private int uvIndex;
        //紫外线指数最大时的时间
        private int uvIndexTime;
        //能见度
        private double visibility;
        //臭氧密度
        private double ozone;
        private double temperatureMin;
        private int temperatureMinTime;
        //deprecated
        private double temperatureMax;
        //deprecated
        private int temperatureMaxTime;
        //deprecated
        private double apparentTemperatureMin;
        //deprecated
        private int apparentTemperatureMinTime;
        //deprecated
        private double apparentTemperatureMax;
        //deprecated
        private int apparentTemperatureMaxTime;
        //降雪量
        private String precipAccumulation;

        public String getPrecipAccumulation() {
            return precipAccumulation;
        }

        public void setPrecipAccumulation(String precipAccumulation) {
            this.precipAccumulation = precipAccumulation;
        }

        public String getPrecipIntensityError() {
            return precipIntensityError;
        }

        public void setPrecipIntensityError(String precipIntensityError) {
            this.precipIntensityError = precipIntensityError;
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

        public int getSunriseTime() {
            return sunriseTime;
        }

        public void setSunriseTime(int sunriseTime) {
            this.sunriseTime = sunriseTime;
        }

        public int getSunsetTime() {
            return sunsetTime;
        }

        public void setSunsetTime(int sunsetTime) {
            this.sunsetTime = sunsetTime;
        }

        public double getMoonPhase() {
            return moonPhase;
        }

        public void setMoonPhase(double moonPhase) {
            this.moonPhase = moonPhase;
        }

        public double getPrecipIntensity() {
            return precipIntensity;
        }

        public void setPrecipIntensity(double precipIntensity) {
            this.precipIntensity = precipIntensity;
        }

        public double getPrecipIntensityMax() {
            return precipIntensityMax;
        }

        public void setPrecipIntensityMax(double precipIntensityMax) {
            this.precipIntensityMax = precipIntensityMax;
        }

        public int getPrecipIntensityMaxTime() {
            return precipIntensityMaxTime;
        }

        public void setPrecipIntensityMaxTime(int precipIntensityMaxTime) {
            this.precipIntensityMaxTime = precipIntensityMaxTime;
        }

        public double getPrecipProbability() {
            return precipProbability;
        }

        public void setPrecipProbability(double precipProbability) {
            this.precipProbability = precipProbability;
        }

        public String getPrecipType() {
            return precipType;
        }

        public void setPrecipType(String precipType) {
            this.precipType = precipType;
        }

        public double getTemperatureHigh() {
            return temperatureHigh;
        }

        public void setTemperatureHigh(double temperatureHigh) {
            this.temperatureHigh = temperatureHigh;
        }

        public int getTemperatureHighTime() {
            return temperatureHighTime;
        }

        public void setTemperatureHighTime(int temperatureHighTime) {
            this.temperatureHighTime = temperatureHighTime;
        }

        public double getTemperatureLow() {
            return temperatureLow;
        }

        public void setTemperatureLow(double temperatureLow) {
            this.temperatureLow = temperatureLow;
        }

        public int getTemperatureLowTime() {
            return temperatureLowTime;
        }

        public void setTemperatureLowTime(int temperatureLowTime) {
            this.temperatureLowTime = temperatureLowTime;
        }

        public double getApparentTemperatureHigh() {
            return apparentTemperatureHigh;
        }

        public void setApparentTemperatureHigh(double apparentTemperatureHigh) {
            this.apparentTemperatureHigh = apparentTemperatureHigh;
        }

        public int getApparentTemperatureHighTime() {
            return apparentTemperatureHighTime;
        }

        public void setApparentTemperatureHighTime(int apparentTemperatureHighTime) {
            this.apparentTemperatureHighTime = apparentTemperatureHighTime;
        }

        public double getApparentTemperatureLow() {
            return apparentTemperatureLow;
        }

        public void setApparentTemperatureLow(double apparentTemperatureLow) {
            this.apparentTemperatureLow = apparentTemperatureLow;
        }

        public int getApparentTemperatureLowTime() {
            return apparentTemperatureLowTime;
        }

        public void setApparentTemperatureLowTime(int apparentTemperatureLowTime) {
            this.apparentTemperatureLowTime = apparentTemperatureLowTime;
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

        public int getWindGustTime() {
            return windGustTime;
        }

        public void setWindGustTime(int windGustTime) {
            this.windGustTime = windGustTime;
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

        public int getUvIndexTime() {
            return uvIndexTime;
        }

        public void setUvIndexTime(int uvIndexTime) {
            this.uvIndexTime = uvIndexTime;
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

        public double getTemperatureMin() {
            return temperatureMin;
        }

        public void setTemperatureMin(double temperatureMin) {
            this.temperatureMin = temperatureMin;
        }

        public int getTemperatureMinTime() {
            return temperatureMinTime;
        }

        public void setTemperatureMinTime(int temperatureMinTime) {
            this.temperatureMinTime = temperatureMinTime;
        }

        public double getTemperatureMax() {
            return temperatureMax;
        }

        public void setTemperatureMax(double temperatureMax) {
            this.temperatureMax = temperatureMax;
        }

        public int getTemperatureMaxTime() {
            return temperatureMaxTime;
        }

        public void setTemperatureMaxTime(int temperatureMaxTime) {
            this.temperatureMaxTime = temperatureMaxTime;
        }

        public double getApparentTemperatureMin() {
            return apparentTemperatureMin;
        }

        public void setApparentTemperatureMin(double apparentTemperatureMin) {
            this.apparentTemperatureMin = apparentTemperatureMin;
        }

        public int getApparentTemperatureMinTime() {
            return apparentTemperatureMinTime;
        }

        public void setApparentTemperatureMinTime(int apparentTemperatureMinTime) {
            this.apparentTemperatureMinTime = apparentTemperatureMinTime;
        }

        public double getApparentTemperatureMax() {
            return apparentTemperatureMax;
        }

        public void setApparentTemperatureMax(double apparentTemperatureMax) {
            this.apparentTemperatureMax = apparentTemperatureMax;
        }

        public int getApparentTemperatureMaxTime() {
            return apparentTemperatureMaxTime;
        }

        public void setApparentTemperatureMaxTime(int apparentTemperatureMaxTime) {
            this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
        }
    }
}
