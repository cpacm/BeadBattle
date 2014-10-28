package com.cpacm.game.assistant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cpacm.game.Bead.BeadType;
import com.cpacm.game.beadbattle.R;
import com.cpacm.game.util.ConstantUtil;

import java.util.Random;

/**
 * Created by cpacm on 2014/10/25.
 */
public class BitmapUtil {
    private static final Random RNG = new Random();
    private static BitmapUtil bitmapUtil = new BitmapUtil();

    private Bitmap bitmap_RED;
    private Bitmap bitmap_BLACK;
    private Bitmap bitmap_GREEN;
    private Bitmap bitmap_YELLOW;
    private Bitmap bitmap_PINK;
    private Bitmap bitmap_DISAPPEAR;

    private BitmapUtil(){
        initBitmapUtil();
    }

    public static BitmapUtil getInstance(){
        return bitmapUtil;
    }

    private void initBitmapUtil(){
        bitmap_RED = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead1);
        bitmap_BLACK = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead2);
        bitmap_GREEN = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead3);
        bitmap_YELLOW = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead4);
        bitmap_PINK = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead5);
        bitmap_DISAPPEAR = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.disappear);
    }

    public Bitmap getBeadBitmap(BeadType type){
        Bitmap bitmap;
        switch(type){
            case RED: bitmap = bitmap_RED;break;
            case BLACK:bitmap = bitmap_BLACK;break;
            case GREEN:bitmap = bitmap_GREEN;break;
            case YELLOW:bitmap = bitmap_YELLOW;break;
            case PINK:bitmap = bitmap_PINK;break;

            default:bitmap = bitmap_RED;break;
        }
        return bitmap;
    }

    //随机的属性珠子
    public BeadType setRandomType(){
        BeadType type = BeadType.RED;
        int roll = RNG.nextInt(5)+1;
        switch (roll){
            case 1:type = BeadType.RED;break;
            case 2:type = BeadType.BLACK;break;
            case 3:type = BeadType.GREEN;break;
            case 4:type = BeadType.YELLOW;break;
            case 5:type = BeadType.PINK;break;
        }
        return type;
    }

    //获得活动图片
    public Bitmap getStatusBitmap(int status){
        Bitmap bitmap;
        switch(status){
            case ConstantUtil.DISAPPEAR:{
                bitmap = bitmap_DISAPPEAR;break;
            }
            default:bitmap = bitmap_DISAPPEAR;break;
        }
        return bitmap;
    }


}
