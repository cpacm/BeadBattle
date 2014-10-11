package com.cpacm.game.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.cpacm.game.Bead.BeadArray;
import com.cpacm.game.beadbattle.R;
import com.cpacm.game.util.ConstantUtil;

/**
 * Created by cpacm on 2014/10/9.
 */
public class BeadGroupView extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap beadPic;
    private SurfaceHolder sfh;
    private Canvas canvas;
    private Paint myPaint;
    private float rx,ry;
    private BeatGroupThread bgThread;
    private BeadArray beadArray;

    public BeadGroupView(Context context) {
        super(context);
        initView();
        initBitmap();
    }

    public BeadGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initBitmap();
    }

    public void initView(){
        setFocusable(true); // 设置焦点
        sfh = this.getHolder();
        sfh.addCallback(this);
        setOnTouchListener(new OnTouchListenerBead());
    }

    public void initBitmap(){
        myPaint = new Paint();
        beadPic = BitmapFactory.decodeResource(getResources(), R.drawable.bead1);
        Log.d("TEST", "pic: " + beadPic.getHeight() + " " + beadPic.getWidth());
        ry = rx = (float) (ConstantUtil.ScreenWidth/(beadPic.getWidth()*6.0));
        Log.d("TEST","rx+ry: " +rx+ " "+ry);
        ConstantUtil.SIZE = beadPic.getWidth();
        beadArray = new BeadArray(this,0,0,ConstantUtil.SIZE);
    }

    public void OnDraw(){
        try {
            canvas = sfh.lockCanvas();
            canvas.save();
            canvas.scale(rx, ry);
            canvas.drawColor(Color.BLACK);
            beadArray.DrawArray(canvas);
            canvas.restore();
        }catch (Exception e){

        }finally {
            if (canvas != null)
                sfh.unlockCanvasAndPost(canvas);  // 将画好的画布提交
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        bgThread = new BeatGroupThread();
        bgThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        bgThread.setRun(false);
    }

    private class BeatGroupThread extends Thread{

        private boolean isRun = false;

        private BeatGroupThread() {
            isRun = true;
        }
        @Override
        public void run() {
            while(isRun){
                OnDraw();
                try {
                    sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }

        public boolean isRun() {
            return isRun;
        }

        public void setRun(boolean isRun) {
            this.isRun = isRun;
        }
    }

    private class OnTouchListenerBead implements OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                beadArray.Update(motionEvent.getX()/rx,motionEvent.getY()/ry);
            }
            else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                beadArray.Update(motionEvent.getX()/rx,motionEvent.getY()/ry);
            }
            else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                beadArray.Clear();
            }
            return true;
        }
    }
}
