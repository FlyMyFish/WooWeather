package com.shichen.wooweather.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/13.
 */
public class DoubleUtils {
    /**
     * 不保留小数，进行四舍五入
     * @param d
     * @return
     */
    public static String removePointUp(double d){
        NumberFormat nf = NumberFormat.getNumberInstance();

        // 不保留小数
        nf.setMaximumFractionDigits(0);

        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);

        return nf.format(d);
    }
}
