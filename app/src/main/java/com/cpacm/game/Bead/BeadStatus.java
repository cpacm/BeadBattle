package com.cpacm.game.Bead;

import android.util.Log;

import com.cpacm.game.util.ConstantUtil;

import java.util.List;

/**
 * 控制珠子的各个状态转换
 * Created by cpacm on 2014/10/11.
 */
public class BeadStatus{

    //掉落参数，单位为ms
    private final static int DROP_PARAM = 50;
    //出现参数
    private final static int DISPLAY_PARAM = 50;
    //消失参数
    private final static int DISAPPEAR_PARAM = 50;

    private BeadArray beadArray;
    private BeadControl beadControl;
    private List<Bead> beadList;
    private Bead b[][];
    private Integer c[][];
    private int status;
    private int process;

    public BeadStatus(BeadArray beadArray) {
        this.beadArray = beadArray;
        this.beadControl = beadArray.getBeadControl();
        beadList = beadControl.getList();
        b = beadArray.getB();
        c = beadControl.getC();
        status = 0;
        process = 0;
    }

    public void StatusChange(){
        if(beadArray.isChangeSwitch()){
            switch(status){
                //准备状态
                case 0:{
                    for (int i = 0; i < beadList.size(); i++) {
                        beadList.get(i).setDisappear(true);
                    }
                    status = 1;
                    process = 0;
                    Log.d("TEST", "status:prepare");
                    break;
                }
                case 1:{
                    //珠子消失状态的动画
                    if(process <= (DISAPPEAR_PARAM/ ConstantUtil.RATE)){
                        process++;
                        Log.d("TEST", "status:display...");
                    }
                    //消失动画结束，重新初始化值，并使珠子进入列表更新状态（下落状态）
                    else{
                        for (int i = 0; i < beadList.size(); i++) {
                            beadList.get(i).setDisappear(false);
                        }
                        for(int i=0;i<ConstantUtil.COUNT;i++){
                            for(int j = ConstantUtil.COUNT-1;j>=0;j--){
                                if(!b[i][j].isSelected()&&c[i][j]>0) {
                                    beadControl.BeadDrop(b[i][j],c[i][j]);
                                    b[i][j+c[i][j]] = b[i][j];
                                }
                            }
                        }
                        process = 0;
                        status = 2;
                        Log.d("TEST", "status:drop");
                    }
                    break;
                }
                //珠子下落状态的动画
                case 2:{
                    if(process <= (DROP_PARAM/ ConstantUtil.RATE)){
                        Log.d("TEST", "status:drop...");
                        process++;
                    }
                    //下落动画结束，重新初始化值，并使珠子进入生成状态
                    else{
                        for(int i=0;i<ConstantUtil.COUNT;i++){
                            for(int j = ConstantUtil.COUNT-1;j>=0;j--){
                                if(b[i][j].isDrop()) {
                                    b[i][j].setDrop(false);
                                }
                            }
                        }
                        process = 0;
                        status = 3;
                        Log.d("TEST", "status:display");
                    }
                    break;
                }
                //生成动画
                case 3:{
                    for(int i=0;i<ConstantUtil.COUNT;i++){
                        for(int j=0;j<c[i][0];j++){
                            b[i][j] = beadArray.getBeadManager().getBeadType(beadArray.getLocX(i), beadArray.getLocY(j), i, j);
                        }
                    }
                    process = 0;
                    status = 0;
                    beadArray.setChangeSwitch(false);
                    beadControl.allClear();
                    Log.d("TEST", "status:close");
                    break;
                }
            }
        }
    }

}
