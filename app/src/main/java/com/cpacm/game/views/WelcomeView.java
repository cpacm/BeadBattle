package com.cpacm.game.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import android.view.SurfaceHolder;

import com.cpacm.game.R;
import com.cpacm.game.util.ConstantUtil;

/**
 * Created by asus on 2014/10/8.
 */
public class WelcomeView extends LineBaseView {

    private Bitmap backGround;
    private SurfaceHolder sfh;
    private Canvas canvas;
    private Paint myPaint;
    private WThread wThread;
    private float rx,ry;

    public WelcomeView(Context context) {
        super(context);
        initView();
        initBitmap();
    }

    public WelcomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initBitmap();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        wThread = new WThread();
        wThread.start();
    }

    public void initView(){
        setFocusable(true); // 设置焦点
        sfh = this.getHolder();
        sfh.addCallback(this);

        //setOnTouchListener(new OnTouchListenerImp());
    }

    public void initBitmap(){
        myPaint = new Paint();
        backGround = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        Log.d("TEST","pic: " + backGround.getHeight()+ " "+backGround.getWidth() );
        rx = (float) (ConstantUtil.ScreenWidth/(backGround.getWidth()*1.0));
        ry = (float) (ConstantUtil.ScreenHeight/(backGround.getHeight()*1.0));
        Log.d("TEST","rx+ry: " +rx+ " "+ry);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        wThread.setRunning(false);
    }

    public void OnDraw(){
        try {
            canvas = sfh.lockCanvas(); // 得到一个canvas实例
            canvas.save();
            canvas.scale(rx,ry);
            myPaint.setAlpha(255);
            canvas.drawBitmap(backGround, 0, 0, myPaint);//绘制图片
            //画的内容是z轴的，后画的会覆盖前面画的
            canvas.restore();
            OnBaseDraw(canvas);

        }
        catch (Exception ex){

        }
        finally{
            if (canvas != null)
                sfh.unlockCanvasAndPost(canvas);  // 将画好的画布提交
        }

        //Log.d("TAG",k+"");

    }
    public class WThread extends Thread{

        private boolean Running;

        public WThread(){
            Running = true;
        }

        @Override
        public void run() {
            while(Running){
                try {
                    sleep(25);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                OnDraw();//调用绘制方法
            }
            super.run();
        }

        public boolean isRunning() {
            return Running;
        }

        public void setRunning(boolean running) {
            Running = running;
        }
    }

}
