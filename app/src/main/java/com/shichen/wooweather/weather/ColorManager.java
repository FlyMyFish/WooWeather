package com.shichen.wooweather.weather;

import android.content.Context;

import com.shichen.wooweather.R;

import java.lang.ref.WeakReference;
import java.util.Calendar;

import static com.shichen.wooweather.basic.Config.AFTERNOON_HOUR;
import static com.shichen.wooweather.basic.Config.MORNING_HOUR;
import static com.shichen.wooweather.basic.Config.NINGHT_HOUR;

/**
 * @author shichen
 */
public class ColorManager {
    private WeakReference<Context> mContext;

    public ColorManager(Context mContext) {
        this.mContext = new WeakReference<>(mContext);
    }

    private Context getContext() {
        return mContext.get();
    }

    public int getWaterColor() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= MORNING_HOUR && hour <= AFTERNOON_HOUR) {
            return getContext().getResources().getColor(R.color.waterMorningColor);
        } else if (hour > AFTERNOON_HOUR && hour < NINGHT_HOUR) {
            return getContext().getResources().getColor(R.color.waterAfternoonColor);
        } else {
            return getContext().getResources().getColor(R.color.waterNightColor);
        }
    }

    public int getWaterLightColor() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= MORNING_HOUR && hour <= AFTERNOON_HOUR) {
            return getContext().getResources().getColor(R.color.waterMorningLightColor);
        } else if (hour > AFTERNOON_HOUR && hour < NINGHT_HOUR) {
            return getContext().getResources().getColor(R.color.waterAfternoonLightColor);
        } else {
            return getContext().getResources().getColor(R.color.waterNightLightColor);
        }
    }

    public int getSkyColor() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= MORNING_HOUR && hour <= AFTERNOON_HOUR) {
            return getContext().getResources().getColor(R.color.skyMorningColor);
        } else if (hour > AFTERNOON_HOUR && hour < NINGHT_HOUR) {
            return getContext().getResources().getColor(R.color.skyAfternoonColor);
        } else {
            return getContext().getResources().getColor(R.color.skyNightColor);
        }
    }

    public int getSkyLightColor() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= MORNING_HOUR && hour <= AFTERNOON_HOUR) {
            return getContext().getResources().getColor(R.color.skyMorningLightColor);
        } else if (hour > AFTERNOON_HOUR && hour < NINGHT_HOUR) {
            return getContext().getResources().getColor(R.color.skyAfternoonLightColor);
        } else {
            return getContext().getResources().getColor(R.color.skyNightLightColor);
        }
    }

    public int getMountainColor() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= MORNING_HOUR && hour <= AFTERNOON_HOUR) {
            return getContext().getResources().getColor(R.color.mountainMorningColor);
        } else if (hour > AFTERNOON_HOUR && hour < NINGHT_HOUR) {
            return getContext().getResources().getColor(R.color.mountainAfternoonColor);
        } else {
            return getContext().getResources().getColor(R.color.mountainNightColor);
        }
    }

    public int getMountainLightColor() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= MORNING_HOUR && hour <= AFTERNOON_HOUR) {
            return getContext().getResources().getColor(R.color.mountainMorningLightColor);
        } else if (hour > AFTERNOON_HOUR && hour < NINGHT_HOUR) {
            return getContext().getResources().getColor(R.color.mountainAfternoonLightColor);
        } else {
            return getContext().getResources().getColor(R.color.mountainNightLightColor);
        }
    }

    public int getSunColor() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= MORNING_HOUR && hour <= AFTERNOON_HOUR) {
            return getContext().getResources().getColor(R.color.sunMorningColor);
        } else if (hour > AFTERNOON_HOUR && hour < NINGHT_HOUR) {
            return getContext().getResources().getColor(R.color.sunAfternoonColor);
        } else {
            return getContext().getResources().getColor(R.color.sunNightColor);
        }
    }

    public int getSunReflectionColor() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= MORNING_HOUR && hour <= 9) {
            return getContext().getResources().getColor(R.color.sunReflectionMorningColor);
        } else if (hour > 9 && hour < 18) {
            return getContext().getResources().getColor(R.color.sunReflectionAfternoonColor);
        } else {
            return getContext().getResources().getColor(R.color.sunReflectionNightColor);
        }
    }

    public int getMountainReflectionColor() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 9) {
            return getContext().getResources().getColor(R.color.mountainReflectionMorningColor);
        } else if (hour > 9 && hour < 18) {
            return getContext().getResources().getColor(R.color.mountainReflectionAfternoonColor);
        } else {
            return getContext().getResources().getColor(R.color.mountainReflectionNightColor);
        }
    }
}
