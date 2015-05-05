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
 * 线条残影类，要调用onBaseDraw（），和在触摸事件中调用setOnTouch()方法
 * Created by cpacm on 2014/10/8.
 */
public abstract class LineBaseView extends SurfaceView implements SurfaceHolder.Callback{

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

    }

    /**
     * 在父类中调用此方法则会画出线
     * @param canvas
     */
    public void OnBaseDraw(Canvas canvas){
        canvas.save();
        //画的内容是z轴的，后画的会覆盖前面画的
        if(dpList.isDraw()) {
            dpList.draw(canvas);
        }
        canvas.restore();

    }

    /*@Override
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
    }*/

    /**
     * 由于当子类继承触摸事件时，该类继承的触摸事件无法运行，所以要人为设置触摸事件
     * 在子类中设置触摸事件
     * @param motionEvent
     */
    public void setTouchEvent(MotionEvent motionEvent){
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
    }
}
