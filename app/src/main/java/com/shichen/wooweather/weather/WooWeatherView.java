package com.shichen.wooweather.weather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/14.
 */
public class WooWeatherView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    private static final String TAG = "WooWeatherView";
    private Context mContext;
    private int mDensity;
    private SurfaceHolder mHolder; // 用于控制SurfaceView
    private Thread t; // 声明一条线程
    private boolean flag; // 线程运行的标识，用于控制线程
    private Canvas mCanvas; // 声明一张画布
    private Paint mPaint;
    private int mScrollY;
    private int mScreenHeight;
    private int mSurfaceWidth;
    private int mSurfaceHeight;
    private int mWaterLeftX, mWaterLeftY, mWaterRightX, mWaterRightY, mLeftMountainHeight;
    private static final int MORNING_COLOR = 0xffFFAF6A;
    private static final int MORNING_SKY_COLOR = 0xffF27732;
    private static final int MOUNTAIN_COLOR = 0xff730700;
    private static final int AFTERNOON_COLOR = 0xff86EBF3;
    private static final int AFTERNOON_SKY_COLOR = 0xff3BBFFD;
    private static final int AFTERNOON_MOUNTAIN_COLOR = 0xff2EACEA;
    private static final int NIGHT_COLOR = 0xff70B9EB;
    private static final int NIGHT_SKY_COLOR = 0xff4059AF;
    private static final int NIGHT_MOUNTAIN_COLOR = 0xff12259D;

    public final int[] waterColors = new int[]{MORNING_COLOR, AFTERNOON_COLOR, NIGHT_COLOR};
    public final int[] skyColors = new int[]{MORNING_SKY_COLOR, AFTERNOON_SKY_COLOR, NIGHT_SKY_COLOR};
    public final int[] mountainColors = new int[]{MOUNTAIN_COLOR, AFTERNOON_MOUNTAIN_COLOR, NIGHT_MOUNTAIN_COLOR};

    public WooWeatherView(Context context) {
        this(context, null);
    }

    public WooWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mDensity = (int) context.getResources().getDisplayMetrics().density;
        this.mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
        setZOrderOnTop(false);
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        t = new Thread(this); // 创建一个线程对象
        flag = true; // 把线程运行的标识设置成true
        t.start(); // 启动线程
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        this.mSurfaceWidth = width;
        this.mSurfaceHeight = height;
        mWaterLeftX = 0;
        mWaterLeftY = mScreenHeight / 5 * 2;
        mWaterRightX = mSurfaceWidth;
        mWaterRightY = mScreenHeight / 5 * 2 - (int) mDensity * 10;
        mLeftMountainHeight = mScreenHeight / 5;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false; // 把线程运行的标识设置成false
    }

    /**
     * 自定义一个方法，在画布上画一个圆
     */
    public void doDraw() {
        mCanvas = mHolder.lockCanvas(); // 获得画布对象，开始对画布画画
        mCanvas.drawColor(Color.WHITE);
        drawSky();
        drawWater();
        drawLeftMountain();
        mHolder.unlockCanvasAndPost(mCanvas); // 完成画画，把画布显示在屏幕上
    }

    private void drawSky() {
        Rect skyRect = new Rect(0, -mScrollY, mSurfaceWidth, mWaterLeftY - mScrollY);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(skyColors[0]);
        mPaint.setPathEffect(null);
        mCanvas.drawRect(skyRect, mPaint);
    }

    private void drawWater() {
        Path waterPath = new Path();
        waterPath.moveTo(mWaterLeftX, mWaterLeftY - mScrollY);
        waterPath.quadTo(mWaterRightX / 5 * 3, mWaterRightY - mScrollY - ((int) (mDensity * 5)), mWaterRightX, mWaterRightY - mScrollY);
        waterPath.lineTo(mWaterRightX, mSurfaceHeight);
        waterPath.lineTo(mWaterLeftX, mSurfaceHeight);
        waterPath.close();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(waterColors[0]);
        mPaint.setPathEffect(null);
        mCanvas.drawPath(waterPath, mPaint);
    }

    private void drawLeftMountain() {
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
        mountainPath.lineTo(mWaterLeftX + mSurfaceWidth / 255 * 150, mWaterLeftY - mScrollY - 8*mDensity);
        mountainPath.close();
        mPaint.setColor(mountainColors[0]);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(10));
        mCanvas.drawPath(mountainPath, mPaint);
    }

    @Override
    public void run() {
        while (flag) {
            doDraw(); // 调用自定义画画方法
            try {
                Thread.sleep(16); // 让线程休息
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        //mScrollY = y;
        super.scrollTo(x,y);
    }
}
