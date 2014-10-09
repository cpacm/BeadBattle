package com.cpacm.game.Bead;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.cpacm.game.IEntity.IBead;

/**
 * Created by cpacm on 2014/10/9.
 */
public abstract class Bead implements IBead{
    protected int locX,locY;
    protected int indexX,indexY;
    protected Bitmap bitmap;
    protected View view;

    public Bead (View view){
        this.view = view;
        initBead();
        initBitmap();
    }

    protected void initBead(){
    }

    protected void initBitmap(){
    }

    public void setLocation(int locX,int locY){
        this.locX = locX;
        this.locY = locY;
    }

    public void setIndex(int indexX,int indexY){
        this.indexX = indexX;
        this.indexY = indexY;
    }

    public void drawBeat(Canvas canvas){
        canvas.drawBitmap(bitmap,locX,locY,new Paint());
    }

}
