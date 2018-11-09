package com.shichen.wooweather.utils;

import android.app.Application;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.shichen.wooweather.data.CityDes;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/9.
 */
public class LocationHelper {
    private static LocationHelper INSTANCE;
    private Application mApplication;
    private LocationClient mLocationClient = null;

    public interface LocationCallback {
        void onSuccess(CityDes cityDes);

        void onFailed();
    }

    private LocationCallback locationCallback;

    public void setLocationCallback(LocationCallback locationCallback) {
        this.locationCallback = locationCallback;
    }

    private class WooLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            int locTypeCode = location.getLocType();
            if (locTypeCode == 61) {
                //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
                //以下只列举部分获取经纬度相关（常用）的结果信息
                //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

                double latitude = location.getLatitude();    //获取纬度信息
                double longitude = location.getLongitude();    //获取经度信息

                //以下只列举部分获取地址相关的结果信息
                String addr = location.getAddrStr();    //获取详细地址信息

                String city = location.getCity();    //获取城市
                String district = location.getDistrict();    //获取区县
                String street = location.getStreet();    //获取街道信息

                //以下只列举部分获取位置描述信息相关的结果
                String locationDescribe = location.getLocationDescribe();    //获取位置描述信息

                CityDes cityDes = new CityDes(city, String.valueOf(latitude), String.valueOf(longitude), district, street);
                if (locationCallback != null) {
                    locationCallback.onSuccess(cityDes);
                }
            }else if (locTypeCode==161){
                //网络定位
                double latitude = location.getLatitude();    //获取纬度信息
                double longitude = location.getLongitude();    //获取经度信息

                //以下只列举部分获取地址相关的结果信息
                String addr = location.getAddrStr();    //获取详细地址信息

                String city = location.getCity();    //获取城市
                String district = location.getDistrict();    //获取区县
                String street = location.getStreet();    //获取街道信息

                //以下只列举部分获取位置描述信息相关的结果
                String locationDescribe = location.getLocationDescribe();    //获取位置描述信息

                CityDes cityDes = new CityDes(city, String.valueOf(latitude), String.valueOf(longitude), district, street);
                if (locationCallback != null) {
                    locationCallback.onSuccess(cityDes);
                }
            }else {
                if (locationCallback!=null){
                    locationCallback.onFailed();
                }
            }
        }
    }

    private LocationHelper(Application context) {
        mApplication = context;
        mLocationClient = new LocationClient(mApplication);
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("wgs84");
        //可选，设置返回经纬度坐标类型，默认GCJ02
        //GCJ02：国测局坐标；
        //BD09ll：百度经纬度坐标；
        //BD09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

        option.setScanSpan(1000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，V7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        option.setIsNeedLocationDescribe(true);
        //可选，是否需要位置描述信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的位置信息，此处必须为true

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.registerLocationListener(new WooLocationListener());
    }

    public static LocationHelper getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new LocationHelper(application);
        }
        return INSTANCE;
    }

    public void start() {
        mLocationClient.start();
    }

    public void stop() {
        mLocationClient.stop();
    }

    private static double PI = 3.1415926535897932384626;

    /**
     * 过测距坐标转GPS坐标
     *
     * @param gcj_lon
     * @param gcj_lat
     * @return
     */
    public static double[] GCJ02ToWGS84(double gcj_lon, double gcj_lat) {
        double a = 6378245.0;
        double ee = 0.00669342162296594323;
        double[] d = new double[2];
        if (outOfChina(gcj_lon, gcj_lat)) {
            return new double[]{gcj_lon, gcj_lat};
        }
        double dlat = transformLat(gcj_lon - 105.0, gcj_lat - 35.0);
        double dlng = transformLng(gcj_lon - 105.0, gcj_lat - 35.0);
        double radlat = gcj_lat / 180.0 * PI;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * PI);
        double mglat = gcj_lat + dlat;
        double mglng = gcj_lon + dlng;
        d[0] = gcj_lon * 2 - mglng;
        d[1] = gcj_lat * 2 - mglat;
        return d;
    }

    private static double transformLat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * PI) + 40.0 * Math.sin(lat / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * PI) + 320 * Math.sin(lat * PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLng(double lng, double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * PI) + 40.0 * Math.sin(lng / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * PI) + 300.0 * Math.sin(lng / 30.0 * PI)) * 2.0 / 3.0;
        return ret;
    }

    //判断坐标是否在中国
    private static boolean outOfChina(double lng, double lat) {
        return (lng < 72.004 || lng > 137.8347) || ((lat < 0.8293 || lat > 55.8271));
    }
}
