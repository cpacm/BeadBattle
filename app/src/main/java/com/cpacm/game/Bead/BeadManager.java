package com.cpacm.game.Bead;

import android.view.View;

import com.cpacm.game.util.ConstantUtil;

import java.util.Random;

/**
 * Created by cpacm on 2014/10/9.
 */
public class BeadManager {

    private View view;
    private BeadType type;
    private static final Random RNG = new Random();

    public BeadManager(View view) {
        this.view = view;
    }

    public Bead BeadType(){
        setRandomType();
        switch(type){
            case RED:return new RedBead(view);
            case GREEN:return new GreenBead(view);
            case YELLOW:return new YellowBead(view);
            case BLACK:return new BlackBead(view);
            case PINK:return new PinkBead(view);
        }
        return null;
    }

    public void setRandomType(){
        switch(RNG.nextInt(ConstantUtil.NUM)){
            case 0:
                type = BeadType.RED;
                break;
            case 1:
                type = BeadType.GREEN;
                break;
            case 2:
                type = BeadType.YELLOW;
                break;
            case 3:
                type = BeadType.BLACK;
                break;
            case 4:
                type = BeadType.PINK;
                break;
        }
    }
}