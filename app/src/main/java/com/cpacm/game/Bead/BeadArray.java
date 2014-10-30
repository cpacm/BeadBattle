package com.cpacm.game.Bead;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.cpacm.game.BeadMessage.MessageDispatcher;
import com.cpacm.game.BeadState.Bead_Disappear;
import com.cpacm.game.BeadState.Bead_Normal;
import com.cpacm.game.BeadState.Bead_Selected;
import com.cpacm.game.util.ConstantUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 珠子的二维数组，游戏的主要场所
 * Created by cpacm on 2014/10/10.
 */
public class BeadArray {
    private static final String TAG = "cpacm";

    private final static int ADVANCE = 0;

    private Bead b[][] = new Bead[ConstantUtil.COUNT][ConstantUtil.COUNT];
    private Integer a[] = new Integer[ConstantUtil.COUNT];
    private int size = ADVANCE;
    private float orgX = ADVANCE, orgY = ADVANCE;
    private List<Bead> beadList, dropList;
    private Bead lastBead = null;
    private Rect drawRect,scaleRect;
    private boolean flag = true;

    /**
     * @param x 原始x坐标
     * @param y 原始y坐标
     */
    public BeadArray(float x, float y) {
        beadList = new ArrayList<Bead>();
        dropList = new ArrayList<Bead>();
        drawRect = new Rect(0,0,ConstantUtil.ScreenWidth,(int)(ConstantUtil.ScreenHeight*ConstantUtil.BeadScreen));
        scaleRect = new Rect();
        setOrgLoc(x, y);
        setSize(ConstantUtil.SIZE);
        initArray();
        initA();
    }

    private void initRect(){
        drawRect.set(ConstantUtil.ScreenWidth,(int)orgY,0,0);
    }

    /**
     * 建立珠子类，填满二维数组
     */
    public void initArray() {
        int k = 0;
        for (int i = 0; i < ConstantUtil.COUNT; i++) {
            for (int j = 0; j < ConstantUtil.COUNT; j++) {
                b[i][j] = new Bead(k);
                b[i][j].setIndex(i, j);
                b[i][j].setLoc(getLocX(j), getLocY(i));
                BeadManager.getInstance().RegisterEntity(b[i][j]);
                k++;
            }
        }
    }

    /**
     * 画出珠子的数组
     *
     * @param canvas
     */
    public void DrawArray(Canvas canvas) {
        for (int i = 0; i < ConstantUtil.COUNT; i++) {
            for (int j = 0; j < ConstantUtil.COUNT; j++) {
                b[i][j].Update(canvas);
            }
        }
    }

    /**
     * 判断珠子是否被选中
     *
     * @param x
     * @param y
     */
    public void BeadSelected(float x, float y) {
        if (BeadManager.getInstance().isNext()) {
            if(flag){
                initMap();
                initRect();
                flag = false;
            }
            for (int i = 0; i < ConstantUtil.COUNT; i++) {
                for (int j = 0; j < ConstantUtil.COUNT; j++) {
                    setTouch(b[i][j], x, y);
                }
            }
        }
    }

    /**
     * 根据传过来的坐标值来判断
     *
     * @param bead
     * @param x
     * @param y
     */
    public void setTouch(Bead bead, float x, float y) {
        if (x >= bead.getLocX() && x <= bead.getLocX() + ConstantUtil.SIZE && y >= bead.getLocY() && y <= bead.getLocY() + ConstantUtil.SIZE) {
            if (!beadList.contains(bead)) {
                if (lastBead == null) {
                    a[bead.getY()]++;
                    AddBead(bead);
                    lastBead = bead;
                    if (bead.getBeadStateManager().getCurrentStatus() == Bead_Normal.getInstance()) {
                        bead.getBeadStateManager().ChangeState(Bead_Selected.getInstance());
                    }
                } else {
                    setSelected(bead);
                }
            }
        }
    }

    public void setSelected(Bead bead) {
        if (bead.getBeadStateManager().getCurrentStatus() == Bead_Normal.getInstance()) {
            if (bead.getType() == lastBead.getType() && Sudoku(bead)) {
                a[bead.getY()]++;
                AddBead(bead);
                lastBead = bead;
                bead.getBeadStateManager().ChangeState(Bead_Selected.getInstance());
            }
        }
    }

    public boolean Sudoku(Bead bead) {
        if ((bead.getX() <= lastBead.getX() + 1 && bead.getY() >= lastBead.getY() - 1) && (bead.getX() >= lastBead.getX() - 1 && bead.getY() <= lastBead.getY() + 1)) {
            return true;
        }
        return false;
    }

    /**
     * 选择结束后，调用这个
     */
    public void Clear() {
        if (beadList.size() > 0) {
            if (beadList.size() > 1) {
                for (int i = 0; i < beadList.size(); i++) {
                    beadList.get(i).getBeadStateManager().ChangeState(Bead_Disappear.getInstance());
                    int id = beadList.get(i).getId();
                    while (id >= ConstantUtil.COUNT) {
                        MessageDispatcher.getInstance().DispatchMessage(0, id, id - ConstantUtil.COUNT, ConstantUtil.DROPCOUNT);
                        id = id - ConstantUtil.COUNT;
                        if (!dropList.contains(BeadManager.getInstance().GetEntityFromId(id))) {
                            dropList.add(BeadManager.getInstance().GetEntityFromId(id));
                        }
                    }
                    beadList.get(i).setId((a[beadList.get(i).getY()] - 1) * ConstantUtil.COUNT + id);
                    a[beadList.get(i).getY()]--;
                }
                for (int i = 0; i < dropList.size(); i++) {
                    MessageDispatcher.getInstance().DispatchMessage(200, dropList.get(i).getId(), dropList.get(i).getId(), ConstantUtil.DROP);
                }
            } else {
                beadList.get(0).getBeadStateManager().ChangeState(Bead_Normal.getInstance());
            }
            beadList.clear();
            dropList.clear();
            lastBead = null;
            initA();
            flag = true;
        }
    }


    public float getLocX(int indexX) {
        return indexX * size + orgX;
    }

    public float getLocY(int indexY) {
        return indexY * size + orgY;
    }

    public void setOrgLoc(float x, float y) {
        this.orgX = x;
        this.orgY = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private void AddBead(Bead bead) {
        int i;
        for (i = 0; i < beadList.size(); i++) {
            if (beadList.get(i).getId() < bead.getId()) {
                break;
            }
        }
        beadList.add(i, bead);
        if(bead.getLocX()<drawRect.left){
            drawRect.left = (int)bead.getLocX();
        }
        if(bead.getLocX()+ConstantUtil.SIZE>drawRect.right){
            drawRect.right = (int)(bead.getLocX())+ConstantUtil.SIZE;
        }
        if(bead.getLocY()>drawRect.bottom){
            drawRect.bottom = (int)(bead.getLocY())+ConstantUtil.SIZE;
        }
    }

    public void initA() {
        for (int i = 0; i < ConstantUtil.COUNT; i++) {
            a[i] = 0;
        }
    }

    public void initMap() {
        for (int i = 0; i < ConstantUtil.COUNT; i++) {
            for (int j = 0; j < ConstantUtil.COUNT; j++) {
                BeadManager.getInstance().RegisterEntity(b[i][j]);
            }
        }
    }
    public Rect getScaleRect() {
        scaleRect.set(drawRect.left,drawRect.top,drawRect.right,drawRect.bottom);
        return scaleRect;
    }
}
