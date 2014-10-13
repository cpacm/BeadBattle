package com.cpacm.game.Bead;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.cpacm.game.IEntity.IBead;

/**
 * Created by cpacm on 2014/10/9.
 */
public abstract class Bead implements IBead{
    protected float locX,locY;
    protected int indexX,indexY;
    protected Bitmap bitmap;
    protected View view;
    protected BeadType beadType;
    protected boolean isTouch = false;//是否触碰
    protected boolean isSelected = false;//是否选择
    protected boolean isDrop = false;//是否掉落
    protected boolean isDisplay = false;//是否显示
    protected boolean isDisappear = false;//是否消失
    protected Paint myPaint;

    protected  int process;
    protected Rect src,dst;

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
        process = 0;
    }

    protected void initBitmap(){
        src = new Rect();
        dst = new Rect();
        myPaint = new Paint();
    }

    public void drawBeat(Canvas canvas){

        if(!isDisplay()){
            canvas.save();
            myPaint.setAlpha(90);
            canvas.drawBitmap(bitmap, locX, locY, myPaint);
            canvas.restore();
            setDisplay(true);
        }
        else if(isDisappear()){
            canvas.save();
            myPaint.setAlpha(80);
            canvas.drawBitmap(bitmap, locX, locY, myPaint);
            canvas.restore();
        }
        else if(isDrop()){
            canvas.save();
            myPaint.setAlpha(120);
            canvas.drawBitmap(bitmap, locX, locY, myPaint);
            canvas.restore();
        }
        else{
            if(isSelected){
                myPaint.setAlpha(40);
                canvas.drawBitmap(bitmap, locX, locY, myPaint);
            }
            else {
                myPaint.setAlpha(225);
                canvas.drawBitmap(bitmap, locX, locY, myPaint);
            }
        }
    }

    public void setLocation(float locX,float locY){
        this.locX = locX;
        this.locY = locY;
    }

    public void setIndex(int indexX,int indexY){
        this.indexX = indexX;
        this.indexY = indexY;
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

    public void setLocY(float locY) {
        this.locY = locY;
    }

    public void setLocX(float locX) {
        this.locX = locX;
    }

    public void setIndexY(int indexY) {
        this.indexY = indexY;
    }

    public void setIndexX(int indexX) {
        this.indexX = indexX;
    }

    public boolean isDisappear() {
        return isDisappear;
    }

    public void setDisappear(boolean isDisappear) {
        this.isDisappear = isDisappear;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    public boolean isDrop() {
        return isDrop;
    }

    public void setDrop(boolean isDrop) {
        this.isDrop = isDrop;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }
}
