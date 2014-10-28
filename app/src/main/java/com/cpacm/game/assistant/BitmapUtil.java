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

    public static Bitmap getBeadBitmap(BeadType type){
        Bitmap bitmap;
        switch(type){
            case RED: bitmap = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead1);break;
            case BLACK:bitmap = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead2);break;
            case GREEN:bitmap = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead3);break;
            case YELLOW:bitmap = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead4);break;
            case PINK:bitmap = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead5);break;
            default:bitmap = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead1);break;
        }
        return bitmap;
    }

    //随机的属性珠子
    public static BeadType setRandomType(){
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
    public static Bitmap getStatusBitmap(int status){
        Bitmap bitmap;
        switch(status){
            case ConstantUtil.DISAPPEAR:{
                bitmap = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.disappear);break;
            }
            default:bitmap = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.disappear);break;
        }
        return bitmap;
    }


}
