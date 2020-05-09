package com.shichen.wooweather.weather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.shichen.wooweather.drawer.MountainDrawer;
import com.shichen.wooweather.drawer.ReflectionDrawer;
import com.shichen.wooweather.drawer.SkyDrawer;
import com.shichen.wooweather.drawer.SunDrawer;
import com.shichen.wooweather.drawer.WaterDrawer;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/14.
 */
public class WooWeatherView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "WooWeatherView";
    private Context mContext;
    private int mDensity;
    private SurfaceHolder mHolder;
    // 用于控制SurfaceView
    private Thread t;
    // 声明一条线程
    private boolean flag;
    // 线程运行的标识，用于控制线程
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

    public WooWeatherView(Context context) {
        this(context, null);
    }

    public WooWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mColorManager = new ColorManager(context);
        mSunDrawer = new SunDrawer();
        mSkyDrawer = new SkyDrawer();
        mWaterDrawer = new WaterDrawer();
        mMountainDrawer =new MountainDrawer();
        mReflectionDrawer =new ReflectionDrawer();
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
        flag = true;
        // 把线程运行的标识设置成true
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
        doDraw();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
        // 把线程运行的标识设置成false
    }

    /**
     * 自定义一个方法，在画布上画一个圆
     */
    public void doDraw() {
        mCanvas = mHolder.lockCanvas();
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
        mHolder.unlockCanvasAndPost(mCanvas);
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

    @Override
    public void scrollTo(int x, int y) {
        //mScrollY = y;
        super.scrollTo(x, y);
    }
}
