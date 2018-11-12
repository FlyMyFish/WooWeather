package com.shichen.wooweather.weather;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.ReplacementSpan;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/12.
 */
public class LinearGradientFontSpan extends ReplacementSpan {
    private int mSize;
    private int startColor;
    private int endColor;

    public LinearGradientFontSpan() {
        this.startColor = Color.parseColor("#30FFFFFF");
        this.endColor = Color.parseColor("#FFFFFFFF");
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        Rect rectF=new Rect();
        paint.getTextBounds(text.toString(),start,end,rectF);
        mSize=rectF.width();
        return mSize;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        int[] colors = new int[]{startColor, endColor, startColor};
        float[] positions = new float[]{0.0f, 0.5f, 1.0f};
        LinearGradient lg = new LinearGradient(0, 0, mSize, 0,
                colors,
                positions,
                Shader.TileMode.REPEAT); //做左到右渐变
        paint.setShader(lg);

        canvas.drawText(text, start, end, x, y, paint);//绘制文字
    }
}
