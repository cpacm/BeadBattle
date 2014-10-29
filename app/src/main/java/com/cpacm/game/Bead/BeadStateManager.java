package com.cpacm.game.Bead;

import android.graphics.Canvas;
import android.util.Log;

import com.cpacm.game.BeadState.Bead_Appear;
import com.cpacm.game.BeadState.Bead_Normal;
import com.cpacm.game.IEntity.IState;
import com.cpacm.game.BeadMessage.Telegram;
import com.cpacm.game.assistant.BitmapUtil;

/**
 * Created by cpacm on 2014/10/9.
 */
public class BeadStateManager {
    private static final String TAG = "cpacm";

    private Bead bead;
    private IState<Bead> CurrentStatus;//当前状态
    private IState<Bead> PreviousStatus;//前一个状态
    private IState<Bead> GlobalStatus;//全局状态

    public BeadStateManager(Bead bead) {
        this.bead = bead;
        setRandomBitmap();
        setCurrentStatus(Bead_Appear.getInstance());
        setPreviousStatus(Bead_Normal.getInstance());
        setGlobalStatus(null);
        CurrentStatus.Enter(bead);
    }

    public BeadStateManager(Bead bead,BeadType type) {
        this.bead = bead;
        bead.setType(type);
        bead.setBitmap(BitmapUtil.getInstance().getBeadBitmap(type));//获取珠子的图片
        setCurrentStatus(Bead_Appear.getInstance());
        setPreviousStatus(Bead_Normal.getInstance());
        setGlobalStatus(null);
        CurrentStatus.Enter(bead);
    }
    //改变状态
    public void ChangeState(IState<Bead> state){
        if(state!=CurrentStatus) {
            Log.d(TAG,"状态变化——"+"id:"+bead.getId()+" cur:"+CurrentStatus.getState()+" new:"+state.getState());
            PreviousStatus = CurrentStatus;
            CurrentStatus.Exit(bead);
            CurrentStatus = state;
            CurrentStatus.Enter(bead);
        }
    }

    //调用状态的运行方法
    public final void Update(Canvas canvas){
        if(GlobalStatus != null) GlobalStatus.Execute(bead,canvas);
        if(CurrentStatus != null) CurrentStatus.Execute(bead,canvas);
    }

    //实体接受到消息并处理
    public final boolean HandleMessage(Telegram msg){

        if(CurrentStatus!=null && CurrentStatus.OnMessage(bead,msg)){
            return true;
        }
        if(GlobalStatus!=null && GlobalStatus.OnMessage(bead,msg)){
            return true;
        }
        return false;
    }

    public void setRandomBitmap(){
        BeadType type = BitmapUtil.getInstance().setRandomType();
        bead.setType(type);
        bead.setBitmap(BitmapUtil.getInstance().getBeadBitmap(type));//获取珠子的图片
        bead.setBitmap_Disappear(BitmapUtil.getInstance().getBeadBitmap_Disappear(type));//获取消失的动画
    }


    //返回到上一个状态
    public void RevertToPreviousState(){
        ChangeState(PreviousStatus);
    }

    public void setCurrentStatus(IState<Bead> currentStatus) {
        CurrentStatus = currentStatus;
    }

    public void setPreviousStatus(IState<Bead> previousStatus) {
        PreviousStatus = previousStatus;
    }

    public void setGlobalStatus(IState<Bead> globalStatus) {
        GlobalStatus = globalStatus;
    }

    public IState<Bead> getCurrentStatus() {
        return CurrentStatus;
    }

    public IState<Bead> getGlobalStatus() {
        return GlobalStatus;
    }

    public IState<Bead> getPreviousStatus() {
        return PreviousStatus;
    }

}
