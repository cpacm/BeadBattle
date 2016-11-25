package com.cpacm.game.assistant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.cpacm.game.Bead.BeadType;
import com.cpacm.game.R;
import com.cpacm.game.util.ConstantUtil;

import java.util.Random;

/**
 * Created by cpacm on 2014/10/25.
 */
public class BitmapUtil {
    private static final Random RNG = new Random();
    private static BitmapUtil bitmapUtil = new BitmapUtil();

    private Bitmap bitmaps[] = new Bitmap[5];
    private Bitmap bitmaps_Disappear[] = new Bitmap[5];
    private Bitmap bitmap;
    private Bitmap bitmap_BackGround;
    private Bitmap bitmap_Cursor;
    private float scaleX,scaleY;

    private BitmapUtil(){
        initBitmapUtil_BackGround();
        initBitmapUtil();
        initBitmapUtil_Disappear();
    }

    public static BitmapUtil getInstance(){
        return bitmapUtil;
    }

    private void initBitmapUtil_BackGround(){
        bitmap_BackGround = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead_bk);
        bitmap_Cursor = BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.cursor);
        float rx = (float) (ConstantUtil.ScreenWidth / (bitmap_BackGround.getWidth() * 1.0));
        float ry = (float) (ConstantUtil.ScreenHeight * ConstantUtil.BeadScreen / (bitmap_BackGround.getHeight() * 1.0));
        scaleX = rx;
        scaleY = ry;
        bitmap_BackGround = CreateBitmap(bitmap_BackGround);
        bitmap_Cursor = CreateBitmap(bitmap_Cursor);
    }

    private Bitmap CreateBitmap(Bitmap preBitmap){
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX,scaleY);
        return Bitmap.createBitmap(preBitmap,0,0,preBitmap.getWidth(),preBitmap.getHeight(),matrix, true);
    }

    private void initBitmapUtil(){
        scaleY = scaleX;
        bitmaps[0] = CreateBitmap(BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead1));
        bitmaps[1] = CreateBitmap(BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead2));
        bitmaps[2] = CreateBitmap(BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead3));
        bitmaps[3] = CreateBitmap(BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead4));
        bitmaps[4] = CreateBitmap(BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.bead5));
        ConstantUtil.SIZE = bitmaps[0].getWidth();
    }

    private void initBitmapUtil_Disappear(){
        scaleY = scaleX;
        bitmaps_Disappear[0] = CreateBitmap(BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.red));
        bitmaps_Disappear[1] = CreateBitmap(BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.blue));
        bitmaps_Disappear[2] = CreateBitmap(BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.green));
        bitmaps_Disappear[3] = CreateBitmap(BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.yellow));
        bitmaps_Disappear[4] = CreateBitmap(BitmapFactory.decodeResource(MyApplication.getAppContext().getResources(), R.drawable.purple));
    }

    public Bitmap getBeadBitmap(BeadType type){
        switch(type){
            case RED: bitmap = bitmaps[0];break;
            case BLUE:bitmap = bitmaps[1];break;
            case GREEN:bitmap = bitmaps[2];break;
            case YELLOW:bitmap = bitmaps[3];break;
            case PURPLE:bitmap = bitmaps[4];break;
            default:bitmap = bitmaps[0];break;
        }
        return bitmap;
    }

    public Bitmap getBeadBitmap_Disappear(BeadType type){
        switch(type){
            case RED: bitmap = bitmaps_Disappear[0];break;
            case BLUE:bitmap = bitmaps_Disappear[1];break;
            case GREEN:bitmap = bitmaps_Disappear[2];break;
            case YELLOW:bitmap = bitmaps_Disappear[3];break;
            case PURPLE:bitmap = bitmaps_Disappear[4];break;
            default:bitmap = bitmaps_Disappear[0];break;
        }
        return bitmap;
    }

    //随机的属性珠子
    public BeadType setRandomType(){
        BeadType type = BeadType.RED;
        int roll = RNG.nextInt(5)+1;
        switch (roll){
            case 1:type = BeadType.RED;break;
            case 2:type = BeadType.BLUE;break;
            case 3:type = BeadType.GREEN;break;
            case 4:type = BeadType.YELLOW;break;
            case 5:type = BeadType.PURPLE;break;
        }
        return type;
    }

    public Bitmap getBitmap_BackGround(){
        return bitmap_BackGround;
    }

    public Bitmap getBitmap_Cursor() {
        return bitmap_Cursor;
    }

    public float getScaleX() {
        return scaleX;
    }
}
