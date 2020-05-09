package com.shichen.wooweather.drawer;


import android.graphics.Canvas;
import android.graphics.Paint;

import com.shichen.wooweather.weather.ColorManager;

public class SunDrawer {
    public void draw(Paint mPaint, Canvas mCanvas, int mWaterLeftX, int mWaterLeftY, int mLeftMountainHeight, int mSurfaceWidth, int mScrollY, ColorManager mColorManager) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColorManager.getSunColor());
        mCanvas.drawCircle(mWaterLeftX + mSurfaceWidth / 255 * 24,
                mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 26,
                mLeftMountainHeight / 5, mPaint);
    }
}
