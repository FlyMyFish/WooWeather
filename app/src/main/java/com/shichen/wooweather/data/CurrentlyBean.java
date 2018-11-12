package com.shichen.wooweather.data;

import android.text.SpannableStringBuilder;
import android.text.Spanned;

import com.shichen.wooweather.weather.LinearGradientFontSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public class CurrentlyBean {
    private int time;
    //概览
    private String summary;
    private String icon;
    //降水量
    private int precipIntensity;
    //降水发生的概率 0-1
    private double precipProbability;
    //空气温度
    private double temperature;
    //体感温度
    private double apparentTemperature;
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
    //风向 0°是正北方，顺时针前进（如果风速为零，则此值将不被定义。）
    private int windBearing;
    //The percentage of sky occluded by clouds, between 0 and 1, inclusive.
    private double cloudCover;
    //紫外线指数
    private int uvIndex;
    //能见度
    private double visibility;
    //臭氧密度
    private double ozone;
    //降水量的分布
    private String precipIntensityError;

    public List<DesAndValue> desAndValueList() {
        List<DesAndValue> desAndValueList = new ArrayList<>();
        desAndValueList.add(new DesAndValue("空气温度:", temperature + "℃"));
        desAndValueList.add(new DesAndValue("体感温度:", apparentTemperature + "℃"));
        desAndValueList.add(new DesAndValue("空气湿度:", humidity * 100 + "%"));
        desAndValueList.add(new DesAndValue("降水量:", precipIntensity + "mm/h"));
        desAndValueList.add(new DesAndValue("降水概率:", precipProbability + ""));
        desAndValueList.add(new DesAndValue("紫外线:", uvIndex + ""));
        desAndValueList.add(new DesAndValue("能见度", visibility + ""));
        desAndValueList.add(new DesAndValue("臭氧密度:", ozone + ""));
        return desAndValueList;
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

    public int getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(int precipIntensity) {
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

    public static class DesAndValue {
        public String desStr;
        public String valueStr;

        public DesAndValue(String desStr, String valueStr) {
            this.desStr = desStr;
            this.valueStr = valueStr;
        }

        public String getDesStr() {
            return desStr;
        }

        public void setDesStr(String desStr) {
            this.desStr = desStr;
        }

        public String getValueStr() {
            return valueStr;
        }

        public void setValueStr(String valueStr) {
            this.valueStr = valueStr;
        }

        public SpannableStringBuilder parseDesAndValue(){
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(desStr+valueStr);
            LinearGradientFontSpan span = new LinearGradientFontSpan();
            spannableStringBuilder.setSpan(span, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableStringBuilder;
        }
    }
}
