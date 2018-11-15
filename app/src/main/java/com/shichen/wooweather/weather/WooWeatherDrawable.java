package com.shichen.wooweather.weather;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
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
    private static final int MORNING_COLOR = 0xffFFAF6A;
    private static final int MORNING_SKY_COLOR = 0xffF27732;
    private static final int MOUNTAIN_COLOR = 0xff330300;
    private static final int AFTERNOON_COLOR = 0xff86EBF3;
    private static final int AFTERNOON_SKY_COLOR = 0xff3BBFFD;
    private static final int AFTERNOON_MOUNTAIN_COLOR = 0xff2EACEA;
    private static final int NIGHT_COLOR = 0xff70B9EB;
    private static final int NIGHT_SKY_COLOR = 0xff4059AF;
    private static final int NIGHT_MOUNTAIN_COLOR = 0xff12259D;

    public final int[] waterColors = new int[]{MORNING_COLOR, AFTERNOON_COLOR, NIGHT_COLOR};
    public final int[] skyColors = new int[]{MORNING_SKY_COLOR, AFTERNOON_SKY_COLOR, NIGHT_SKY_COLOR};
    public final int[] sunColors = new int[]{0xffFFFDE6};
    public final int[] mountainColors = new int[]{MOUNTAIN_COLOR, AFTERNOON_MOUNTAIN_COLOR, NIGHT_MOUNTAIN_COLOR};

    public WooWeatherDrawable(View parent) {
        this.parent = parent;
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
        drawSky(canvas);
        drawSun(canvas);
        drawLeftMountain(canvas);
        drawWater(canvas);
        drawReflection(canvas);
    }

    private void drawSky(Canvas mCanvas) {
        Rect skyRect = new Rect(0, -mScrollY, mSurfaceWidth, mWaterLeftY - mScrollY);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(skyColors[0]);
        mCanvas.drawRect(skyRect, mPaint);
        RadialGradient radialGradient = new RadialGradient(mWaterLeftX + mSurfaceWidth / 255 * 24,
                mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 26,
                mSurfaceWidth,
                0xffFFEDA7, MORNING_SKY_COLOR, Shader.TileMode.CLAMP);
        mPaint.setShader(radialGradient);
        mCanvas.drawCircle(mWaterLeftX + mSurfaceWidth / 255 * 24,
                mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 26,
                mSurfaceWidth, mPaint);
        reStorePaint();
    }

    private void drawSun(Canvas mCanvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(sunColors[0]);
        mCanvas.drawCircle(mWaterLeftX + mSurfaceWidth / 255 * 24,
                mWaterLeftY - mScrollY - mLeftMountainHeight + mLeftMountainHeight / 80 * 26,
                mLeftMountainHeight / 5, mPaint);
        reStorePaint();
    }

    private void drawWater(Canvas mCanvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(waterColors[0]);
        int sc = mCanvas.saveLayer(mWaterLeftX, mWaterRightY - mScrollY - ((mDensity * 5)), mWaterRightX, mSurfaceHeight, mPaint, Canvas.ALL_SAVE_FLAG);
        Path waterPath = new Path();
        waterPath.moveTo(mWaterLeftX, mWaterLeftY - mScrollY);
        waterPath.quadTo(mWaterRightX / 5 * 3, mWaterRightY - mScrollY - ((mDensity * 5)), mWaterRightX, mWaterRightY - mScrollY);
        waterPath.lineTo(mWaterRightX, mSurfaceHeight);
        waterPath.lineTo(mWaterLeftX, mSurfaceHeight);
        waterPath.close();

        int[] colors = new int[]{MORNING_COLOR, 0xffFCD79B, MORNING_COLOR};
        float[] positions = new float[]{0.0f, 0.5f, 1.0f};
        LinearGradient linearGradient = new LinearGradient(mWaterLeftX + mSurfaceWidth / 255 * 24 - mSurfaceWidth, mWaterLeftY - mScrollY,
                mWaterLeftX + mSurfaceWidth / 255 * 24 + mSurfaceWidth, mWaterLeftY - mScrollY, colors, positions, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
        mCanvas.drawPath(waterPath, mPaint);

        mCanvas.restoreToCount(sc);

        mCanvas.save();
        reStorePaint();
    }

    private void drawLeftMountain(Canvas mCanvas) {
        Path mountainPath = new Path();
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
        mPaint.setColor(mountainColors[0]);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(10));
        mCanvas.drawPath(mountainPath, mPaint);
        reStorePaint();
        int sc = mCanvas.saveLayer(0, 0, mWaterRightX, mWaterLeftY, mPaint, Canvas.ALL_SAVE_FLAG);
        mPaint.setPathEffect(new CornerPathEffect(10));
        mCanvas.drawPath(mountainPath, mPaint);
        reStorePaint();
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mPaint.setColor(0xffB93000);
        RadialGradient circleOneGradient = new RadialGradient(mWaterLeftX + mSurfaceWidth / 255 * 50,
                mWaterLeftY - mScrollY - mLeftMountainHeight,
                mLeftMountainHeight / 5 * 4,
                0xffB72C00, MOUNTAIN_COLOR, Shader.TileMode.CLAMP);
        mPaint.setShader(circleOneGradient);
        mCanvas.drawCircle(mWaterLeftX + mSurfaceWidth / 255 * 50,
                mWaterLeftY - mScrollY - mLeftMountainHeight,
                mLeftMountainHeight / 5 * 4,
                mPaint);
        RadialGradient circleTwoGradient = new RadialGradient(mWaterLeftX + mSurfaceWidth / 255 * 150,
                mWaterLeftY - mScrollY - mLeftMountainHeight / 2,
                mLeftMountainHeight / 2,
                0xffB72C00, MOUNTAIN_COLOR, Shader.TileMode.CLAMP);
        mPaint.setShader(circleTwoGradient);
        mCanvas.drawCircle(mWaterLeftX + mSurfaceWidth / 255 * 150,
                mWaterLeftY - mScrollY - mLeftMountainHeight / 2,
                mLeftMountainHeight / 2,
                mPaint);
        mCanvas.restoreToCount(sc);
        reStorePaint();
    }

    public void drawReflection(Canvas mCanvas) {
        int offset = 35;
        int offsetX = 5;
        float blurValue=5.0f;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xffFCEBC6);
        mPaint.setMaskFilter(new BlurMaskFilter(blurValue, BlurMaskFilter.Blur.NORMAL));
        mCanvas.drawCircle(mWaterLeftX - mDensity * offsetX + mSurfaceWidth / 255 * 24,
                mWaterLeftY - mScrollY + mLeftMountainHeight - mLeftMountainHeight / 80 * 26 - mDensity * offset,
                mLeftMountainHeight / 5, mPaint);
        reStorePaint();
        Path mountainPath = new Path();
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
        mPaint.setColor(0xffCC8752);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(10));
        int sc = mCanvas.saveLayer(-mDensity * offsetX, mWaterLeftY - mScrollY - mDensity * offset, mWaterRightX, mWaterLeftY - mScrollY + mLeftMountainHeight, mPaint, Canvas.ALL_SAVE_FLAG);
        mPaint.setMaskFilter(new BlurMaskFilter(blurValue, BlurMaskFilter.Blur.NORMAL));
        mCanvas.drawPath(mountainPath, mPaint);
        reStorePaint();
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(waterColors[0]);
        Path waterPath = new Path();
        waterPath.moveTo(mWaterLeftX - mDensity * offsetX, mWaterLeftY - mScrollY);
        waterPath.quadTo(mWaterRightX / 5 * 3, mWaterRightY - mScrollY - ((mDensity * 5)), mWaterRightX, mWaterRightY - mScrollY);
        waterPath.lineTo(mWaterRightX, mWaterLeftY - mScrollY - mDensity * offset);
        waterPath.lineTo(mWaterLeftX - mDensity * offsetX, mWaterLeftY - mScrollY - mDensity * offset);
        waterPath.close();
        mCanvas.drawPath(waterPath, mPaint);
        reStorePaint();
        mCanvas.restoreToCount(sc);
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
