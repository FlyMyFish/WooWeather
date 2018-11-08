package com.shichen.wooweather.data.source.local;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.shichen.wooweather.data.CityDes;
import com.shichen.wooweather.data.source.CityDesSource;
import com.shichen.wooweather.util.SingleExecutors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/8.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CityDesLocalSourceTest {

    private CityDesLocalSource mCityDesLocalSource;
    private WooWeatherDataBase mWooWeatherDataBase;

    @Before
    public void setUp() {
        mWooWeatherDataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                WooWeatherDataBase.class)
                .build();
        CityDesDao cityDesDao = mWooWeatherDataBase.cityDesDao();
        CityDesLocalSource.clearInstance();
        mCityDesLocalSource = CityDesLocalSource.getInstance(new SingleExecutors(), cityDesDao);
    }

    @After
    public void tearDown() {
        mWooWeatherDataBase.close();
        CityDesLocalSource.clearInstance();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(mCityDesLocalSource);
    }

    @Test
    public void getCityDes() {
        final CityDes mCityDes = new CityDes("zhengzhou", "31.11251", "114.12512", "管城回族区", "商城路");
        mCityDesLocalSource.saveCityDes(mCityDes);
        mCityDesLocalSource.getCityDes("zhengzhou", new CityDesSource.LoadCityDesCallBack() {
            @Override
            public void onCityDesLoaded(CityDes cityDes) {
                assertThat(cityDes, is(mCityDes));
            }

            @Override
            public void onNoDataAvailable(String msg) {
                fail("Callback error");
            }
        });
    }

    @Test
    public void saveCityDes() {
        final CityDes mCityDes = new CityDes("zhengzhou", "31.11251", "114.12512", "管城回族区", "商城路");
        mCityDesLocalSource.saveCityDes(mCityDes);
        mCityDesLocalSource.getCityDes("zhengzhou", new CityDesSource.LoadCityDesCallBack() {
            @Override
            public void onCityDesLoaded(CityDes cityDes) {
                assertThat(cityDes, is(mCityDes));
            }

            @Override
            public void onNoDataAvailable(String msg) {
                fail("Callback error");
            }
        });
    }

    @Test
    public void updateCityDes() {
        final CityDes mCityDes = new CityDes("zhengzhou", "31.11251", "114.12512");
        mCityDesLocalSource.saveCityDes(mCityDes);
        final CityDes newCityDes = new CityDes("zhengzhou", "31.11251", "114.12512", "管城回族区", "商城路");
        mCityDesLocalSource.updateCityDes(newCityDes);
        mCityDesLocalSource.getCityDes("zhengzhou", new CityDesSource.LoadCityDesCallBack() {
            @Override
            public void onCityDesLoaded(CityDes cityDes) {
                assertThat(cityDes, is(newCityDes));
            }

            @Override
            public void onNoDataAvailable(String msg) {
                fail("Callback error");
            }
        });
    }

    @Test
    public void deleteCityDes() {
        final CityDes mCityDes = new CityDes("zhengzhou", "31.11251", "114.12512");
        mCityDesLocalSource.saveCityDes(mCityDes);
        mCityDesLocalSource.deleteCityDes("zhengzhou");
        mCityDesLocalSource.getCityDes("zhengzhou", new CityDesSource.LoadCityDesCallBack() {
            @Override
            public void onCityDesLoaded(CityDes cityDes) {
                fail("delete error");
            }

            @Override
            public void onNoDataAvailable(String msg) {
                assertNotNull(msg);
            }
        });
    }
}