package com.cpacm.game.BeadState;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.cpacm.game.Bead.Bead;
import com.cpacm.game.BeadMessage.Telegram;
import com.cpacm.game.IEntity.IState;
import com.cpacm.game.util.ConstantUtil;


/**
 * Created by cpacm on 2014/10/27.
 */
public class Bead_Selected implements IState<Bead> {

    private Matrix mMatrix;
    private Paint paint = new Paint();
    private static final Bead_Selected bead_Selected = new Bead_Selected();

    private Bead_Selected(){}

    @Override
    public void Enter(Bead bead) {
        mMatrix = new Matrix();
        bead.setOffset(0);
    }

    @Override
    public void Execute(Bead bead, Canvas canvas) {
        mMatrix.setTranslate(bead.getLocX()+bead.getOffset(),bead.getLocY());
        bead.setOffset(bead.getOffset()>0.5f?-0.5f:bead.getOffset()+0.1f);
        mMatrix.postScale(0.98f,0.98f,bead.getLocX()+ ConstantUtil.SIZE/2f,bead.getLocY()+ ConstantUtil.SIZE/2f);
        paint.setAlpha(160);
        canvas.drawBitmap(bead.getBitmap(), mMatrix, paint);
    }

    @Override
    public void Exit(Bead bead) {
        bead.setOffset(0);
    }

    @Override
    public String getState() {
        return "selected";
    }

    @Override
    public boolean OnMessage(Bead bead, Telegram msg) {

        return false;
    }

    //饿汉单例模式
    public static Bead_Selected getInstance() {
        return bead_Selected;
    }
}
