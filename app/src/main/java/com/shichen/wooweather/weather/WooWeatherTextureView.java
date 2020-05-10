package com.shichen.wooweather.weather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.TextureView;

import com.shichen.wooweather.drawer.MountainDrawer;
import com.shichen.wooweather.drawer.ReflectionDrawer;
import com.shichen.wooweather.drawer.SkyDrawer;
import com.shichen.wooweather.drawer.SunDrawer;
import com.shichen.wooweather.drawer.WaterDrawer;

/**
 * @author shichen 754314442@qq.com
 * Created by shichen on 2020/5/9.
 */
public class WooWeatherTextureView extends TextureView implements TextureView.SurfaceTextureListener {
    private int mDensity;
    private Canvas mCanvas;
    // 声明一张画布
    private Paint mPaint;
    private int mScrollY;
    private int mScreenHeight;
    private int mSurfaceWidth;
    private int mSurfaceHeight;
    /**
     * mWaterLeftX：天空与水与屏幕的左侧交界处X坐标
     * mWaterLeftY：天空与水与屏幕的左侧交界处Y坐标
     * mWaterRightX：天空与水与屏幕的右侧交界处X坐标
     * mWaterRightY：天空与水与屏幕的右侧交界处Y坐标
     * mLeftMountainHeight：山的最高值
     */
    private int mWaterLeftX, mWaterLeftY, mWaterRightX, mWaterRightY, mLeftMountainHeight;
    private ColorManager mColorManager;
    private SunDrawer mSunDrawer;
    private SkyDrawer mSkyDrawer;
    private WaterDrawer mWaterDrawer;
    private MountainDrawer mMountainDrawer;
    private ReflectionDrawer mReflectionDrawer;
    public WooWeatherTextureView(Context context) {
        super(context);
    }

    public WooWeatherTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSurfaceTextureListener(this);
        mColorManager = new ColorManager(context);
        mSunDrawer = new SunDrawer();
        mSkyDrawer = new SkyDrawer();
        mWaterDrawer = new WaterDrawer();
        mMountainDrawer =new MountainDrawer();
        mReflectionDrawer =new ReflectionDrawer();
        this.mDensity = (int) context.getResources().getDisplayMetrics().density;
        this.mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        this.mSurfaceWidth = width;
        this.mSurfaceHeight = height;
        mWaterLeftX = 0;
        mWaterLeftY = mScreenHeight / 5 * 2;
        mWaterRightX = mSurfaceWidth;
        mWaterRightY = mScreenHeight / 5 * 2 - (int) mDensity * 10;
        mLeftMountainHeight = mScreenHeight / 5;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    doDraw();
                    /*try {
                        Thread.sleep(16);
                    }catch (Exception e){
                        e.printStackTrace();
                    }*/
                }
            }
        }).start();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    /**
     * 自定义一个方法，在画布上画一个圆
     */
    public void doDraw() {
        mCanvas = lockCanvas();
        // 获得画布对象，开始对画布画画
        mCanvas.drawColor(Color.WHITE);
        mSkyDrawer.draw(mPaint,mCanvas,mScrollY,mSurfaceWidth,mWaterLeftX,mWaterLeftY,mLeftMountainHeight,mColorManager);
        reStorePaint();
        mSunDrawer.draw(mPaint, mCanvas, mWaterLeftX, mWaterLeftY, mLeftMountainHeight, mSurfaceWidth, mScrollY, mColorManager);
        reStorePaint();
        mMountainDrawer.draw(mPaint,mCanvas,mWaterLeftX,mWaterLeftY,mScrollY,mLeftMountainHeight,mSurfaceWidth,mDensity,mWaterRightX,mColorManager);
        reStorePaint();
        mWaterDrawer.draw(mPaint,mCanvas,mWaterLeftX,mWaterLeftY,mWaterRightX,mWaterRightY,mScrollY,mSurfaceHeight,mSurfaceWidth,mDensity,mColorManager);
        reStorePaint();
        mReflectionDrawer.draw(mPaint,mCanvas,mWaterLeftX,mDensity,mSurfaceWidth,mWaterLeftY,mScrollY,mLeftMountainHeight,mWaterRightX,mWaterRightY,mColorManager);
        reStorePaint();
        unlockCanvasAndPost(mCanvas);
        // 完成画画，把画布显示在屏幕上
    }

    private void reStorePaint() {
        if (mPaint != null) {
            mPaint.setXfermode(null);
            mPaint.setShader(null);
            mPaint.setMaskFilter(null);
            mPaint.setPathEffect(null);
        }
    }
}
