package com.shichen.wooweather.drawer;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.shichen.wooweather.weather.ColorManager;

public class ReflectionDrawer {
    private Path mountainPath = new Path();
    private int offset = 35;
    private int offsetX = 5;
    private float blurValue = 5.0f;

    public void draw(Paint mPaint, Canvas mCanvas, int mWaterLeftX, int mDensity, int mSurfaceWidth, int mWaterLeftY, int mScrollY, int mLeftMountainHeight, int mWaterRightX, int mWaterRightY, ColorManager mColorManager) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColorManager.getSunReflectionColor());
        mPaint.setMaskFilter(new BlurMaskFilter(blurValue, BlurMaskFilter.Blur.NORMAL));
        mCanvas.drawCircle(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 24,
                mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 26 - mDensity * offset,
                mLeftMountainHeight / 5, mPaint);
        reStorePaint(mPaint);
        mountainPath.moveTo(mWaterLeftX - mDensity * offsetX, mWaterLeftY - mScrollY - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX, mWaterLeftY - mScrollY + mLeftMountainHeight - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 13, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 15 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 13, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 17 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 18, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 23 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 21, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 23 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 24, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 26 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 24, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 32 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 26, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 34 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 30, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 34 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 32, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 36 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 43, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 46 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 44, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 38 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 50, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 31 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 55, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 29 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 57, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 29 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 59, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 25 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 61, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 26 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 63, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 22 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 64, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 16 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 64, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 11 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 66, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 9 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 71, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 12 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 73, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 12 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 80, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 20 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 84, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 28 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 92, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 37 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 98, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 51 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 107, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 47 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 112, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 50 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 115, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 50 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 127, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 61 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 128, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 65 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 131, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 69 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 142, mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 76 - mDensity * offset);
        mountainPath.lineTo(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 150, mWaterLeftY - mScrollY + 8 * mDensity - mDensity * offset);
        mountainPath.close();
        mPaint.setColor(mColorManager.getMountainReflectionColor());
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(10));
        int sc = mCanvas.saveLayer(-mDensity * offsetX, mWaterLeftY - mScrollY - mDensity * offset, mWaterRightX, mWaterLeftY - mScrollY + mLeftMountainHeight, mPaint, Canvas.ALL_SAVE_FLAG);
        mPaint.setMaskFilter(new BlurMaskFilter(blurValue, BlurMaskFilter.Blur.NORMAL));
        mCanvas.drawPath(mountainPath, mPaint);
        reStorePaint(mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColorManager.getWaterColor());
        Path waterPath = new Path();
        waterPath.moveTo(mWaterLeftX - mDensity * offsetX, mWaterLeftY - mScrollY);
        waterPath.quadTo(mWaterRightX / 5 * 3, mWaterRightY - mScrollY - ((mDensity * 5)), mWaterRightX, mWaterRightY - mScrollY);
        waterPath.lineTo(mWaterRightX, mWaterLeftY - mScrollY - mDensity * offset);
        waterPath.lineTo(mWaterLeftX - mDensity * offsetX, mWaterLeftY - mScrollY - mDensity * offset);
        waterPath.close();
        mCanvas.drawPath(waterPath, mPaint);
        reStorePaint(mPaint);
        mCanvas.restoreToCount(sc);
    }

    private void reStorePaint(Paint mPaint) {
        if (mPaint != null) {
            mPaint.setXfermode(null);
            mPaint.setShader(null);
            mPaint.setMaskFilter(null);
            mPaint.setPathEffect(null);
        }
    }

}
