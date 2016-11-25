package com.cpacm.game.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.cpacm.game.Bead.BeadArray;
import com.cpacm.game.assistant.BitmapUtil;
import com.cpacm.game.thread.BeadGroupThread;
import com.cpacm.game.util.ConstantUtil;

/**
 * 游戏主体，滑珠
 * <p/>
 * Created by cpacm on 2014/10/9.
 */
public class BeadGroupView extends LineBaseView {

    private Bitmap bead_bk;
    private SurfaceHolder sfh;
    private Canvas canvas;
    private Paint myPaint;
    private BeadGroupThread bgThread;
    private BeadArray beadArray;

    public BeadGroupView(Context context) {
        super(context);
        BitmapUtil.getInstance();
        initView();
        initBitmap();
    }

    public BeadGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BitmapUtil.getInstance();
        initView();
        initBitmap();
    }

    public void initView() {
        setFocusable(true); // 设置焦点
        sfh = this.getHolder();
        sfh.addCallback(this);
        setOnTouchListener(new OnTouchListenerBead());
    }

    public void initBitmap() {
        myPaint = new Paint();
        //珠子的二维数组类
        Log.d("cpacm", "Size" + ConstantUtil.SIZE);
        ConstantUtil.OFFSETY = getOffsetY();
        Log.d("TEST", "offX" + getOffsetX() + "offY" + getOffsetY());
        beadArray = new BeadArray(getOffsetX(), getOffsetY());
    }


    public void OnDraw() {
        try {
            canvas = sfh.lockCanvas(beadArray.getScaleRect());
            canvas.drawBitmap(BitmapUtil.getInstance().getBitmap_BackGround(), 0, 0, myPaint);
            beadArray.DrawArray(canvas);
            //绘制珠子的二维数组
            //线条残影的绘制
            OnBaseDraw(canvas);
        } catch (Exception e) {

        } finally {
            if (canvas != null)
                sfh.unlockCanvasAndPost(canvas);  // 将画好的画布提交
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //绘制珠子的线程
        bgThread = new BeadGroupThread(this);
        bgThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        bgThread.setRun(false);
    }


    private class OnTouchListenerBead implements OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            //线条残影的触摸设置
            setTouchEvent(motionEvent);
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                beadArray.BeadSelected(motionEvent.getX(), motionEvent.getY());
            } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                beadArray.BeadSelected(motionEvent.getX() , motionEvent.getY());
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                beadArray.Clear();
            }
            return true;
        }
    }

    public float getOffsetX() {
        return (ConstantUtil.ScreenWidth - ConstantUtil.SIZE * ConstantUtil.COUNT ) / 2 ;
    }

    public float getOffsetY() {
        return (ConstantUtil.ScreenHeight * ConstantUtil.BeadScreen - ConstantUtil.SIZE * ConstantUtil.COUNT );
    }


}
