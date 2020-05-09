package com.shichen.wooweather.weather;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;

public class SkyDrawer {
    private Rect skyRect = new Rect();

    public void draw(Paint mPaint, Canvas mCanvas, int mScrollY, int mSurfaceWidth, int mWaterLeftX, int mWaterLeftY, int mLeftMountainHeight, ColorManager mColorManager) {
        skyRect.left = 0;
        skyRect.top = -mScrollY;
        skyRect.right = mSurfaceWidth;
        skyRect.bottom = mWaterLeftY - mScrollY;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColorManager.getSkyColor());
        mCanvas.drawRect(skyRect, mPaint);
        RadialGradient radialGradient = new RadialGradient(mWaterLeftX + mSurfaceWidth / 255 * 24,
                mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 26,
                mSurfaceWidth,
                mColorManager.getSkyLightColor(), mColorManager.getSkyColor(), Shader.TileMode.CLAMP);
        mPaint.setShader(radialGradient);
        mCanvas.drawCircle(mWaterLeftX + mSurfaceWidth / 255 * 24,
                mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 26,
                mSurfaceWidth, mPaint);
    }
}
