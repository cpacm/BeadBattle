package com.cpacm.game.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.cpacm.game.assistant.DragPointer;

/**
 * 线条残影类，要调用onBaseDraw
 * Created by cpacm on 2014/10/8.
 */
public abstract class LineBaseView extends SurfaceView implements SurfaceHolder.Callback{

    private Paint basePaint;
    protected DragPointer dpList;

    public LineBaseView(Context context) {
        super(context);
        initBaseView();
        initBaseBitmap();
    }

    public LineBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBaseView();
        initBaseBitmap();
    }

    public void initBaseView(){
       // setFocusable(true); // 设置焦点
        dpList = new DragPointer();
        //setOnTouchListener(new OnTouchListenerImp());
    }

    public void initBaseBitmap(){
        basePaint = new Paint();
        basePaint.setColor(Color.BLACK);
        // myPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        basePaint.setStyle(Paint.Style.STROKE);
        basePaint.setStrokeWidth(20.f);
        basePaint.setStrokeCap(Paint.Cap.ROUND);
        basePaint.setAntiAlias(true);
    }

    public void OnBaseDraw(Canvas canvas){
        canvas.save();
        //画的内容是z轴的，后画的会覆盖前面画的
        if(dpList.isDraw()) {
            for (int i = 0; i < dpList.getNum() - 1; i++) {
                final Path path = new Path();
                basePaint.setAlpha((i * 8) < 255 ? (i * 8) : 255);
                path.moveTo(dpList.getLocX().get(i), dpList.getLocY().get(i));
                final float x2 = (dpList.getLocX().get(i + 1) + dpList.getLocX().get(i)) / 2;
                final float y2 = (dpList.getLocY().get(i + 1) + dpList.getLocY().get(i)) / 2;
                path.quadTo(x2, y2, dpList.getLocX().get(i + 1), dpList.getLocY().get(i + 1));
                canvas.drawPath(path, basePaint);
            }
        }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            dpList.initPoint(motionEvent.getX(),motionEvent.getY());
            dpList.setDraw(true);
        }
        else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
            dpList.setPoint(motionEvent.getX(),motionEvent.getY());
        }
        else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
            dpList.setDraw(false);
        }
        return true;
    }
}
