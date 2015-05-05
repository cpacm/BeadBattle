package com.cpacm.game.assistant;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import com.cpacm.game.util.ConstantUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 拖动点的辅助类
 * Created by asus on 2014/10/9.
 */
public class DragPointer {

    private final static int NUM = 30;
    private List<Float> LocX = new ArrayList<Float>();
    private List<Float> LocY = new ArrayList<Float>();
    private boolean IsDraw = false;
    private Bitmap cursor;
    private Paint paint;
    private Matrix mMatrix;

    public DragPointer(){
        init();
    }

    public void init(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
        mMatrix = new Matrix();
        // myPaint.setStyle(Paint.Style.FILL_AND_STROKE);
      //  paint.setStyle(Paint.Style.STROKE);
       // paint.setStrokeWidth(20.f);
       // paint.setStrokeCap(Paint.Cap.ROUND);
        //paint.setAntiAlias(true);
        cursor = BitmapUtil.getInstance().getBitmap_Cursor();
    }

    public void initPoint(float x,float y){
        LocX.clear();
        LocY.clear();
        for(int i=0;i< NUM;i++){
            LocX.add(x);
            LocY.add(y);
        }
    }

    public void setPoint(float x,float y){
        LocX.remove(0);
        LocX.add(x);
        LocY.remove(0);
        LocY.add(y);
    }

    public void draw(Canvas canvas){
        //final Path path = new Path();

        //path.moveTo(dpList.getLocX().get(i), dpList.getLocY().get(i));
        //final float x2 = (dpList.getLocX().get(i + 1) + dpList.getLocX().get(i)) / 2;
        //final float y2 = (dpList.getLocY().get(i + 1) + dpList.getLocY().get(i)) / 2;
        //path.quadTo(x2, y2, dpList.getLocX().get(i + 1), dpList.getLocY().get(i + 1));
        //canvas.drawPath(path, basePaint);
        for (int i = 0; i < LocX.size() - 1; i++) {
            paint.setAlpha((i * 12) < 255 ? (i * 12) : 255);
            mMatrix.setTranslate(LocX.get(i), LocY.get(i));
            mMatrix.postScale((i + 1.0f) / (NUM/2 * 1.0f), (i + 1.0f) / (NUM/2 * 1.0f), LocX.get(i) + ConstantUtil.SIZE / 4f, LocY.get(i) + ConstantUtil.SIZE / 4f);
            canvas.drawBitmap(cursor,mMatrix,paint);
        }

    }

    public List<Float> getLocX() {
        return LocX;
    }

    public void setLocX(List<Float> locX) {
        LocX = locX;
    }

    public List<Float> getLocY() {
        return LocY;
    }

    public void setLocY(List<Float> locY) {
        LocY = locY;
    }

    public boolean isDraw() {
        return IsDraw;
    }

    public void setDraw(boolean isDraw) {
        IsDraw = isDraw;
    }

    public static int getNum() {
        return NUM;
    }
}
