package com.shichen.wooweather.weather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/15.
 */
public class WooWeatherDrawable extends Drawable {
    private final String TAG = "WooWeatherDrawable";
    private int mScrollY;
    private View parent;
    private Context mContext;
    private int mDensity;
    private Paint mPaint;
    private int mScreenHeight;
    private int mSurfaceWidth;
    private int mSurfaceHeight;
    private int mWaterLeftX, mWaterLeftY, mWaterRightX, mWaterRightY, mLeftMountainHeight;
    private ColorManager mColorManager;
    private SunDrawer mSunDrawer;
    private SkyDrawer mSkyDrawer;
    private WaterDrawer mWaterDrawer;
    private MountainDrawer mMountainDrawer;
    private ReflectionDrawer mReflectionDrawer;

    public WooWeatherDrawable(View parent) {
        this.parent = parent;
        mColorManager = new ColorManager(parent.getContext());
        mSunDrawer = new SunDrawer();
        mSkyDrawer = new SkyDrawer();
        mWaterDrawer = new WaterDrawer();
        mMountainDrawer = new MountainDrawer();
        mReflectionDrawer = new ReflectionDrawer();
        mContext = parent.getContext();
        mDensity = (int) mContext.getResources().getDisplayMetrics().density;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        this.mScreenHeight = mContext.getResources().getDisplayMetrics().heightPixels;
    }

    private void reStorePaint() {
        if (mPaint != null) {
            mPaint.setXfermode(null);
            mPaint.setShader(null);
            mPaint.setMaskFilter(null);
            mPaint.setPathEffect(null);
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        this.mSurfaceWidth = parent.getWidth();
        this.mSurfaceHeight = parent.getHeight();
        mWaterLeftX = 0;
        mWaterLeftY = mScreenHeight / 5 * 2;
        mWaterRightX = mSurfaceWidth;
        mWaterRightY = mScreenHeight / 5 * 2 - mDensity * 10;
        mLeftMountainHeight = mScreenHeight / 5;
        canvas.drawColor(Color.WHITE);
        mSkyDrawer.draw(mPaint, canvas, mScrollY, mSurfaceWidth, mWaterLeftX, mWaterLeftY, mLeftMountainHeight, mColorManager);
        reStorePaint();
        mSunDrawer.draw(mPaint, canvas, mWaterLeftX, mWaterLeftY, mLeftMountainHeight, mSurfaceWidth, mScrollY, mColorManager);
        reStorePaint();
        mMountainDrawer.draw(mPaint, canvas, mWaterLeftX, mWaterLeftY, mScrollY, mLeftMountainHeight, mSurfaceWidth, mDensity, mWaterRightX, mColorManager);
        reStorePaint();
        mWaterDrawer.draw(mPaint, canvas, mWaterLeftX, mWaterLeftY, mWaterRightX, mWaterRightY, mScrollY, mSurfaceHeight, mSurfaceWidth, mDensity, mColorManager);
        reStorePaint();
        mReflectionDrawer.draw(mPaint, canvas, mWaterLeftX, mDensity, mSurfaceWidth, mWaterLeftY, mScrollY, mLeftMountainHeight, mWaterRightX, mWaterRightY, mColorManager);
        reStorePaint();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }
}
