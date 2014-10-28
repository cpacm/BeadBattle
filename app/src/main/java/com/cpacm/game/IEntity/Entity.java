package com.cpacm.game.IEntity;

import android.util.Log;

import com.cpacm.game.IEntity.IEntity;

/**
 * 珠子的基本类
 * Created by cpacm on 2014/10/9.
 */
public abstract class Entity implements IEntity {

    protected int id;
    private int x,y;
    private float locX,locY;

    public void setLoc(float locX,float locY){
        setLocX(locX);
        setLocY(locY);
    }

    public void setIndex(int x,int y){
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getLocX() {
        return locX;
    }

    public void setLocX(float locX) {
        this.locX = locX;
    }

    public float getLocY() {
        return locY;
    }

    public void setLocY(float locY) {
        this.locY = locY;
    }

    public int getId(){
        return id;
    }
}
