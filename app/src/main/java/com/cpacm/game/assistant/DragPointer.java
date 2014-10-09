package com.cpacm.game.assistant;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 拖动点的辅助类
 * Created by asus on 2014/10/9.
 */
public class DragPointer {

    private final static int NUM = 30;
    private List<Float> LocX = new ArrayList<Float>();
    private List<Float> LocY = new ArrayList<Float>();
    private boolean IsDraw = false;

    public DragPointer(){
    }

    public void initPoint(float x,float y){
        LocX.clear();
        LocY.clear();
        for(int i=0;i< NUM;i++){
            LocX.add(x);
            LocY.add(y);
        }
    }

    public void setPoint(float x,float y){
        LocX.remove(0);
        LocX.add(x);
        LocY.remove(0);
        LocY.add(y);
    }

    public List<Float> getLocX() {
        return LocX;
    }

    public void setLocX(List<Float> locX) {
        LocX = locX;
    }

    public List<Float> getLocY() {
        return LocY;
    }

    public void setLocY(List<Float> locY) {
        LocY = locY;
    }

    public boolean isDraw() {
        return IsDraw;
    }

    public void setDraw(boolean isDraw) {
        IsDraw = isDraw;
    }

    public static int getNum() {
        return NUM;
    }
}
