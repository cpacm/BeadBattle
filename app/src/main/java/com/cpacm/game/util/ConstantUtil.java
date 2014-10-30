package com.cpacm.game.util;

/**
 * Created by cpacm on 2014/10/8.
 */
public class ConstantUtil {
    //珠子的总个数
    public static final int MAX = 36;

    //珠子的种类
    public static final int NUM = 5;

    //每行珠子的个数
    public static final int COUNT= 6;

    //每个珠子的大小
    public static int SIZE = 120;

    //珠子刷新的频率
    public static final long RATE = 20;

    //珠子所占屏幕的比例
    public static final float BeadScreen = 5/9.0f;
    //战争所占屏幕的比例
    public static final float BattleScreen = 4/9.0f;
    //屏幕缩放的比例
    //public static float SCALE = 1f;
    //手机屏幕的各个参数
    public static int ScreenWidth;
    public static int ScreenHeight;
    public static float density;
    public static int DPI;

    //y轴的偏移值
    public static float OFFSETY = 0;

    //珠子的状态参数
    public static final int APPEAR = 0;
    public static final int NORMAL = 1;
    public static final int SELECTED = 2;
    public static final int DISAPPEAR = 3;
    public static final int DROP = 4;
    public static final int DROPCOUNT = 5;
}
