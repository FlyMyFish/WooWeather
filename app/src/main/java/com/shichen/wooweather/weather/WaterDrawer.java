package com.shichen.wooweather.weather;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;

public class WaterDrawer {
    private Path waterPath = new Path();
    private int[] gradientColors = new int[3];

    public void draw(Paint mPaint, Canvas mCanvas, int mWaterLeftX, int mWaterLeftY, int mWaterRightX, int mWaterRightY, int mScrollY, int mSurfaceHeight, int mSurfaceWidth, float mDensity, ColorManager mColorManager) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColorManager.getWaterColor());
        int sc = mCanvas.saveLayer(mWaterLeftX, mWaterRightY - mScrollY - ((mDensity * 5)), mWaterRightX, mSurfaceHeight, mPaint, Canvas.ALL_SAVE_FLAG);
        waterPath.moveTo(mWaterLeftX, mWaterLeftY - mScrollY);
        waterPath.quadTo(mWaterRightX / 5 * 3, mWaterRightY - mScrollY - ((mDensity * 5)), mWaterRightX, mWaterRightY - mScrollY);
        waterPath.lineTo(mWaterRightX, mSurfaceHeight);
        waterPath.lineTo(mWaterLeftX, mSurfaceHeight);
        waterPath.close();
        gradientColors[0] = mColorManager.getWaterColor();
        gradientColors[1] = mColorManager.getWaterLightColor();
        gradientColors[2] = mColorManager.getWaterColor();
        float[] positions = new float[]{0.0f, 0.5f, 1.0f};
        LinearGradient linearGradient = new LinearGradient(mWaterLeftX + mSurfaceWidth / 255 * 24 - mSurfaceWidth, mWaterLeftY - mScrollY,
                mWaterLeftX + mSurfaceWidth / 255 * 24 + mSurfaceWidth, mWaterLeftY - mScrollY, gradientColors, positions, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        mCanvas.drawPath(waterPath, mPaint);

        mCanvas.restoreToCount(sc);

        mCanvas.save();
    }
}
