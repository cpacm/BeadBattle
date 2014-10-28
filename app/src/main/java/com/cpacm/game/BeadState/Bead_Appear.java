package com.cpacm.game.BeadState;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.nfc.Tag;
import android.util.Log;

import com.cpacm.game.Bead.Bead;
import com.cpacm.game.IEntity.IState;
import com.cpacm.game.BeadMessage.Telegram;
import com.cpacm.game.util.ConstantUtil;

/**
 * Created by cpacm on 2014/10/20.
 */
public class Bead_Appear implements IState<Bead> {
    private static final Bead_Appear bead_Appear = new Bead_Appear();
    private Paint paint;
    private Matrix mMatrix;
    private int AnimaTime = 15;//动画的帧数

    private Bead_Appear(){
        paint = new Paint();
    }

    @Override
    public void Enter(Bead bead) {
        bead.setOffset(0);
        setBeadLocY(bead);
        setBeadX(bead);
        mMatrix = new Matrix();

    }

    @Override
    public void Execute(Bead bead, Canvas canvas) {
        if(bead.getOffset()<=AnimaTime) {
            float scale = bead.getOffset()/AnimaTime;
            mMatrix.setTranslate(bead.getLocX(),bead.getLocY());
            mMatrix.postScale(scale,scale,bead.getLocX()+ ConstantUtil.SIZE/2f,bead.getLocY()+ ConstantUtil.SIZE/2f);
            paint.setAlpha((int)(bead.getOffset()*(255f/AnimaTime)));
            canvas.drawBitmap(bead.getBitmap(), mMatrix, paint);
            bead.setOffset(bead.getOffset()+1);
        }
        else{
            canvas.drawBitmap(bead.getBitmap(),  bead.getLocX(), bead.getLocY(),paint);
            bead.getBeadStateManager().ChangeState(Bead_Normal.getInstance());
        }
    }

    @Override
    public void Exit(Bead bead) {
        bead.setOffset(0);
    }

    @Override
    public String getState() {
        return "appear";
    }

    @Override
    public boolean OnMessage(Bead bead, Telegram msg) {

        return false;
    }

    //饿汉单例模式
    public static Bead_Appear getInstance() {
        return bead_Appear;
    }

    private void setBeadLocY(Bead bead){
        bead.setLocY(bead.getId()/ConstantUtil.COUNT*ConstantUtil.SIZE+ConstantUtil.OFFSETY);
    }

    private void setBeadX(Bead bead){
        bead.setX(bead.getId()/ConstantUtil.COUNT);
    }
}
