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
import android.view.SurfaceView;

import com.cpacm.game.beadbattle.R;
import com.cpacm.game.util.ConstantUtil;

/**
 * Created by cpacm on 2014/10/9.
 */
public class BeadGroupView extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap backGround;
    private SurfaceHolder sfh;
    private Canvas canvas;
    private Paint myPaint;
    private float rx,ry;

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

        //setOnTouchListener(new OnTouchListenerImp());
    }

    public void initBitmap(){
        myPaint = new Paint();
        backGround = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        Log.d("TEST", "pic: " + backGround.getHeight() + " " + backGround.getWidth());
        rx = (float) (ConstantUtil.ScreenWidth/(backGround.getWidth()*1.0));
        ry = (float) (ConstantUtil.ScreenHeight/(backGround.getHeight()*1.0));
        Log.d("TEST","rx+ry: " +rx+ " "+ry);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
