package com.cpacm.game.Bead;

import android.graphics.Canvas;

import com.cpacm.game.util.ConstantUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpacm on 2014/10/10.
 */
public class BeadControl {

    private Bead lastBead = null;
    private List<Bead> beadList = new ArrayList<Bead>();

    public BeadControl() {
    }

    public void drawBeat(Bead bead,Canvas canvas){
        bead.drawBeat(canvas);
    }

    public void setTouch(Bead bead,float x,float y){
        if(x>=bead.getLocX() && x<=bead.getLocX()+ ConstantUtil.SIZE && y>=bead.getLocY() && y<=bead.getLocY()+ConstantUtil.SIZE){
            bead.setTouch(true);
            if(lastBead == null && !bead.isSelected()){
                beadList.add(bead);
                lastBead = bead;
                bead.setSelected(true);

            }else{
                setSelected(bead);
            }
        }
        else{
            bead.setTouch(false);
        }
    }

    public void Clear(Bead bead){
        bead.setTouch(false);
        bead.setSelected(false);
    }

    public void allClear(){
        lastBead = null;
        beadList.clear();
    }

    public void setSelected(Bead bead){
        if (bead.getBeadType() == lastBead.getBeadType() && Sudoku(bead) && !bead.isSelected()) {
                beadList.add(bead);
                lastBead = bead;
                bead.setSelected(true);
        }
    }

    public boolean Sudoku(Bead bead){
        if((bead.getIndexX()<=lastBead.getIndexX()+1&&bead.getIndexY()>=lastBead.getIndexY()-1)&&(bead.getIndexX()>=lastBead.getIndexX()-1&&bead.getIndexY()<=lastBead.getIndexY()+1)){
            return true;
        }
        return false;
    }

    public void ChangeBead(Bead b1,Bead b2){
        b1.setIndex(b2.getIndexX(),b2.getIndexY());
        b1.setLocation(b2.getLocX(),b2.getLocY());

    }

    public List<Bead> getList(){
        return beadList;
    }


}
