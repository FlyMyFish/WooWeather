package com.shichen.wooweather.weather;

import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;

public class MountainDrawer {
    private Path mountainPath = new Path();
    public void draw(Paint mPaint,Canvas mCanvas,int mWaterLeftX,int mWaterLeftY,int mScrollY,int mLeftMountainHeight,int mSurfaceWidth,int mDensity,int mWaterRightX,ColorManager mColorManager){
        mountainPath.moveTo(mWaterLeftX, mWaterLeftY - mScrollY);
        mountainPath.lineTo(mWaterLeftX, mWaterLeftY - mScrollY - mLeftMountainHeight);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 13, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 15);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 13, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 17);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 18, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 23);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 21, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 23);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 24, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 26);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 24, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 32);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 26, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 34);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 30, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 34);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 32, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 36);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 43, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 46);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 44, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 38);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 50, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 31);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 55, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 29);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 57, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 29);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 59, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 25);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 61, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 26);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 63, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 22);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 64, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 16);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 64, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 11);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 66, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 9);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 71, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 12);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 73, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 12);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 80, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 20);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 84, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 28);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 92, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 37);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 98, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 51);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 107, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 47);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 112, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 50);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 115, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 50);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 127, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 61);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 128, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 65);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 131, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 69);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 142, mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 76);
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 150, mWaterLeftY - mScrollY - 8 * mDensity);
        mountainPath.close();
        mPaint.setColor(mColorManager.getMountainColor());
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(10));
        mCanvas.drawPath(mountainPath, mPaint);
        reStorePaint(mPaint);
        int sc = mCanvas.saveLayer(0, 0, mWaterRightX, mWaterLeftY, mPaint, Canvas.ALL_SAVE_FLAG);
        mPaint.setPathEffect(new CornerPathEffect(10));
        mCanvas.drawPath(mountainPath, mPaint);
        reStorePaint(mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        RadialGradient circleOneGradient = new RadialGradient(mWaterLeftX + mSurfaceWidth / 255 * 50,
                mWaterLeftY - mScrollY - mLeftMountainHeight,
                mLeftMountainHeight / 5 * 4,
                mColorManager.getMountainLightColor(), mColorManager.getMountainColor(), Shader.TileMode.CLAMP);
        mPaint.setShader(circleOneGradient);
        mCanvas.drawCircle(mWaterLeftX + mSurfaceWidth / 255 * 50,
                mWaterLeftY - mScrollY - mLeftMountainHeight,
                mLeftMountainHeight / 5 * 4,
                mPaint);
        RadialGradient circleTwoGradient = new RadialGradient(mWaterLeftX + mSurfaceWidth / 255 * 150,
                mWaterLeftY - mScrollY - mLeftMountainHeight / 2,
                mLeftMountainHeight / 2,
                mColorManager.getMountainLightColor(), mColorManager.getMountainColor(), Shader.TileMode.CLAMP);
        mPaint.setShader(circleTwoGradient);
        mCanvas.drawCircle(mWaterLeftX + mSurfaceWidth / 255 * 150,
                mWaterLeftY - mScrollY - mLeftMountainHeight / 2,
                mLeftMountainHeight / 2,
                mPaint);
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
