package com.cpacm.game.Bead;

import android.graphics.Canvas;
import android.view.View;

import com.cpacm.game.util.ConstantUtil;

import java.util.List;

/**
 * Created by cpacm on 2014/10/10.
 */
public class BeadArray {
    private final static int ADVANCE = 0;

    private Bead b[][] = new Bead [ConstantUtil.COUNT][ConstantUtil.COUNT];
    private View view;
    private BeadManager beadManager;
    private int size = ADVANCE;
    private float orgX = ADVANCE,orgY = ADVANCE;
    private BeadControl beadControl;
    private List<Bead> beadList;
    private boolean ChangeSwitch = false;

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
        for(int i=0;i<ConstantUtil.COUNT;i++){
            for(int j=0;j<ConstantUtil.COUNT;j++){
                b[i][j] = beadManager.getBeadType(getLocX(i),getLocY(j),i,j);
            }
        }
    }

    public void DrawArray(Canvas canvas){
        for(int i=0;i<ConstantUtil.COUNT;i++){
            for(int j=0;j<ConstantUtil.COUNT;j++){
                beadControl.drawBeat(b[i][j],canvas);
            }
        }
    }

    public void Update(float x,float y){
        for(int i=0;i<ConstantUtil.COUNT;i++){
            for(int j=0;j<ConstantUtil.COUNT;j++){
                beadControl.setTouch(b[i][j], x, y);
            }
        }
    }

    public float getLocX(int indexX){
        return indexX*size+orgX;
    }

    public float getLocY(int indexY){
        return indexY*size+orgY;
    }

    public void setOrgLoc(float x,float y){
        this.orgX = x;
        this.orgY = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BeadControl getBeadControl() {
        return beadControl;
    }

    public boolean isChangeSwitch() {
        return ChangeSwitch;
    }

    public void setChangeSwitch(boolean changeSwitch) {
        beadList = beadControl.getList();
        if(beadList.size()>1){
            ChangeSwitch = changeSwitch;
        }
        else if(beadList.size()<=1){
            beadControl.Clear(beadList.get(0));
            beadControl.allClear();
        }
    }

    public BeadManager getBeadManager() {
        return beadManager;
    }

    public Bead[][] getB() {
        return b;
    }
}
