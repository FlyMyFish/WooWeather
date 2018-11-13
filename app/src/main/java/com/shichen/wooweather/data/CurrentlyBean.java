package com.shichen.wooweather.data;

import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import com.shichen.wooweather.R;
import com.shichen.wooweather.utils.DoubleUtils;
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
    public String summary;
    public String icon;
    //降水量
    public int precipIntensity;
    //降水发生的概率 0-1
    public double precipProbability;
    //空气温度
    public double temperature;
    //体感温度
    public double apparentTemperature;
    //露珠
    public double dewPoint;
    //空气湿度 between 0 and 1
    public double humidity;
    //海平面空气压力 单位:毫巴
    public double pressure;
    //风速为每小时英里
    public double windSpeed;
    //风速每小时英里数
    public double windGust;
    //风向 0°是正北方，顺时针前进（如果风速为零，则此值将不被定义。）
    public int windBearing;
    //The percentage of sky occluded by clouds, between 0 and 1, inclusive.
    public double cloudCover;
    //紫外线指数
    public int uvIndex;
    //能见度
    public double visibility;
    //臭氧密度
    public double ozone;
    //降水量的分布
    public String precipIntensityError;

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

    public int getIconResId() {
        return parseIcon(icon);
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

    public String getTemperature() {
        return DoubleUtils.removePointUp(temperature) + "°";
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getApparentTemperature() {
        return "体感温度:" + DoubleUtils.removePointUp(apparentTemperature) + "℃";
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

    public String getHumidity() {
        return "空气湿度:" + DoubleUtils.removePointUp(humidity * 100) + "%";
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

        public SpannableStringBuilder parseDesAndValue() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(desStr + valueStr);
            LinearGradientFontSpan span = new LinearGradientFontSpan();
            spannableStringBuilder.setSpan(span, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableStringBuilder;
        }
    }

    //clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night
    //白天局部多云
    private static final String PARTLY_CLOUDY_DAY = "partly-cloudy-day";
    //夜晚局部多云
    private static final String PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";
    //白天晴朗
    private static final String CLEAR_DAY = "clear-day";
    //夜晚晴朗
    private static final String CLEAR_NIGHT = "clear-night";
    //雨
    private static final String RAIN = "rain";
    //雪
    private static final String SNOW = "snow";
    //雨夹雪
    private static final String SLEET = "sleet";
    //大风
    private static final String WIND = "wind";
    //大雾
    private static final String FOG = "fog";
    //阴天
    private static final String CLOUDY = "cloudy";

    public static int parseIcon(@NonNull String icon) {
        switch (icon) {
            case CLEAR_DAY:
                return R.drawable.ic_weather_clear_day;
            case CLEAR_NIGHT:
                return R.drawable.ic_weather_clear_night;
            case RAIN:
                return R.drawable.ic_weather_rain;
            case SNOW:
                return R.drawable.ic_weather_snow;
            case SLEET:
                return R.drawable.ic_weather_sleet;
            case WIND:
                return R.drawable.ic_weather_wind;
            case FOG:
                return R.drawable.ic_weather_fog;
            case CLOUDY:
                return R.drawable.ic_weather_cloudy;
            case PARTLY_CLOUDY_DAY:
                return R.drawable.ic_weather_partly_cloudy_day;
            case PARTLY_CLOUDY_NIGHT:
                return R.drawable.ic_weather_partly_cloudy_night;
            default:
                return R.drawable.ic_weather_undefined;
        }
    }
}
