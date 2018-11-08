package com.shichen.wooweather.data.source.remote;

import android.support.test.runner.AndroidJUnit4;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.shichen.wooweather.basic.Config;
import com.shichen.wooweather.data.BlockBean;
import com.shichen.wooweather.data.CityDes;
import com.shichen.wooweather.data.GPSBean;
import com.shichen.wooweather.utils.GsonUtils;
import com.shichen.wooweather.utils.LogUtilsWoo;
import com.shichen.wooweather.utils.RequestHelper;
import com.shichen.wooweather.utils.WooException;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/8.
 */
@RunWith(AndroidJUnit4.class)
public class CityDesRemoteSourceTest {
    private final String TAG = "CityDesRemoteSourceTest";

    @Test
    public void getCityDes() {
        final StringBuilder url = new StringBuilder(Config.Q_TO_CITY);
        url.append("q=");
        url.append("zhengzhou");
        RequestHelper.getInstance().GetRequest(url.toString(), null, new RequestHelper.RequestCallBack() {
            @Override
            public void onSuccess(String responseBody) {
                LogUtilsWoo.Log(TAG, responseBody);
                try {
                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(responseBody);
                    if (element.isJsonObject()) {
                        GPSBean gpsBean = GsonUtils.getInstance().get().fromJson(element, GPSBean.class);
                        if (gpsBean != null) {
                            getCityDesName("zhengzhou", gpsBean.getLatitude(), gpsBean.getLongitude());
                        } else {

                        }
                    } else {

                    }
                } catch (JsonSyntaxException e) {

                }
            }

            @Override
            public void onFailed(Exception e) {
                if (e instanceof WooException) {
                    WooException wooException = (WooException) e;
                    LogUtilsWoo.Log(TAG, "code=" + wooException.getResponseCode() + ";msg=" + wooException.getMsg());

                } else {
                    LogUtilsWoo.Log(TAG, e.toString());
                }
            }
        });
    }

    private void getCityDesName(final String query, final String lat, final String lon) {
        final Map<String, String> params = new HashMap<>();
        params.put("lat", lat);
        params.put("lon", lon);

        RequestHelper.getInstance().GetRequest(Config.GPS_TO_CITY, params, new RequestHelper.RequestCallBack() {
            @Override
            public void onSuccess(String responseBody) {
                LogUtilsWoo.Log(TAG, responseBody);
                try {
                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(responseBody);
                    if (element.isJsonObject()) {
                        BlockBean blockBean = GsonUtils.getInstance().get().fromJson(element, BlockBean.class);
                        if (blockBean != null) {
                            final CityDes cityDes = new CityDes(query, lat, lon, blockBean.getName(), blockBean.getStreet());
                            LogUtilsWoo.Log(TAG, "CityDes -> " + cityDes.toString());
                            assertNotNull(cityDes);
                        } else {
                        }
                    } else {
                    }
                } catch (JsonSyntaxException e) {

                }
            }

            @Override
            public void onFailed(Exception e) {
                if (e instanceof WooException) {
                    WooException wooException = (WooException) e;
                    LogUtilsWoo.Log(TAG, "code=" + wooException.getResponseCode() + ";msg=" + wooException.getMsg());
                } else {
                    LogUtilsWoo.Log(TAG, e.toString());
                }
            }
        });
    }
}