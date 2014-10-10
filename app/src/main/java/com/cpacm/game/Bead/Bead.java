package com.cpacm.game.Bead;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.cpacm.game.IEntity.IBead;
import com.cpacm.game.util.ConstantUtil;

/**
 * Created by cpacm on 2014/10/9.
 */
public abstract class Bead implements IBead{
    protected float locX,locY;
    protected int indexX,indexY;
    protected Bitmap bitmap;
    protected View view;
    protected BeadType beadType;
    protected int size;
    protected boolean isTouch = false;
    protected boolean isSelected = false;
    protected Paint myPaint;

    public Bead (View view){
        this.view = view;
        initBead();
        initBitmap();
    }

    public Bead(View view,float locX,float locY,int indexX,int indexY){
        this.view = view;
        setLocation(locX,locY);
        setIndex(indexX,indexY);
        initBead();
        initBitmap();
    }

    protected void initBead(){
    }

    protected void initBitmap(){
        myPaint = new Paint();
    }

    public void setLocation(float locX,float locY){
        this.locX = locX;
        this.locY = locY;
    }

    public void setIndex(int indexX,int indexY){
        this.indexX = indexX;
        this.indexY = indexY;
    }

    public void drawBeat(Canvas canvas){
        if(isSelected){
            myPaint.setAlpha(100);
            //canvas.drawBitmap(bitmap, locX, locY, myPaint);
        }
        else {
            myPaint.setAlpha(225);
            canvas.drawBitmap(bitmap, locX, locY, myPaint);
        }
    }

    public boolean isTouch() {
        return isTouch;
    }

    public void setTouch(boolean isTouch) {
        this.isTouch = isTouch;
    }

    public BeadType getBeadType() {
        return beadType;
    }

    public void setBeadType(BeadType beadType) {
        this.beadType = beadType;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public float getLocY() {
        return locY;
    }

    public float getLocX() {
        return locX;
    }

    public int getIndexX() {
        return indexX;
    }

    public int getIndexY() {
        return indexY;
    }
}
