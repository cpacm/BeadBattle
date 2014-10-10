package com.cpacm.game.Bead;

import android.graphics.Canvas;
import android.view.View;

/**
 * Created by cpacm on 2014/10/10.
 */
public class BeadArray {
    private final static int COUNT = 6;
    private final static int ADVANCE = 0;

    private Bead b[][] = new Bead [COUNT][COUNT];
    private View view;
    private BeadManager beadManager;
    private int size = ADVANCE;
    private float orgX = ADVANCE,orgY = ADVANCE;
    private int indexX,indexY;
    private BeadControl beadControl;

    public BeadArray(View view){
        this.view = view;
        beadManager = new BeadManager(this.view);
        beadControl = new BeadControl();
        //initArray();未设定坐标和大小，不能初始化，请在设定后认为调用初始化函数
    }

    public BeadArray(View view,float x,float y,int size){
        this.view = view;
        beadManager = new BeadManager(this.view);
        beadControl = new BeadControl();
        setOrgLoc(x,y);
        setSize(size);
        initArray();
    }

    public void initArray(){
        for(int i=0;i<COUNT;i++){
            for(int j=0;j<COUNT;j++){
                b[i][j] = beadManager.getBeadType(getLocX(i),getLocY(j),i,j);
            }
        }
    }

    public void DrawArray(Canvas canvas){
        for(int i=0;i<COUNT;i++){
            for(int j=0;j<COUNT;j++){
                beadControl.drawBeat(b[i][j],canvas);
            }
        }
    }

    public void Update(float x,float y){
        setIndexX(x);
        setIndexY(y);
        for(int i=0;i<COUNT;i++){
            for(int j=0;j<COUNT;j++){
                beadControl.setTouch(b[i][j], x, y);
            }
        }
    }

    public void setIndexX(float x) {
        indexX = (int)((x-orgX)/size);
    }

    public void setIndexY(float y) {
        indexY = (int)((y-orgY)/size);
    }

    public void Clear(){
        for(int i=0;i<COUNT;i++){
            for(int j=0;j<COUNT;j++){
                beadControl.Clear(b[i][j]);
            }
        }
    }

    private float getLocX(int indexX){
        return indexX*size+orgX;
    }

    private float getLocY(int indexY){
        return indexY*size+orgY;
    }

    public void setOrgLoc(float x,float y){
        this.orgX = x;
        this.orgY = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static int getCount() {
        return COUNT;
    }
}
