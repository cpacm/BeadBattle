package com.cpacm.game.BeadState;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.cpacm.game.Bead.Bead;
import com.cpacm.game.Bead.BeadManager;
import com.cpacm.game.BeadMessage.MessageDispatcher;
import com.cpacm.game.BeadMessage.Telegram;
import com.cpacm.game.IEntity.IState;
import com.cpacm.game.assistant.BitmapUtil;
import com.cpacm.game.util.ConstantUtil;

/**
 * Created by cpacm on 2014/10/27.
 */
public class Bead_Disappear implements IState<Bead> {
    private Paint paint = new Paint();
    public static Bead_Disappear bead_disappear = new Bead_Disappear();
    private Rect src,dst;

    private Bead_Disappear(){
        src = new Rect();
        dst = new Rect();
    }

    @Override
    public void Enter(Bead bead) {
        BeadManager.getInstance().addDisappearCount(1);
        bead.setOffset(0);
    }

    @Override
    public void Execute(Bead bead, Canvas canvas) {
        if(bead.getOffset()<15) {
            setDst(bead);
            setSrc((int) (bead.getOffset()/1));
            canvas.drawBitmap(bead.getBitmap_Disappear(),src,dst,paint);
            bead.setOffset(bead.getOffset()+1);
        }
        else{
            canvas.drawBitmap(bead.getBitmap_Disappear(),src,dst,paint);
            bead.getBeadStateManager().ChangeState(Bead_Appear.getInstance());
        }
    }

    @Override
    public void Exit(Bead bead) {
        bead.setOffset(0);
        bead.getBeadStateManager().setRandomBitmap();
        setBeadLocY(bead);
        setBeadX(bead);
        BeadManager.getInstance().addDisappearCount(-1);
    }

    private void setSrc(int index){
        int y = index/5;
        int x = index%5;
        src.set(x*ConstantUtil.SIZE,y*ConstantUtil.SIZE,(x+1)*ConstantUtil.SIZE,(y+1)*ConstantUtil.SIZE);
    }
    private void setDst(Bead bead){
        dst.set((int)bead.getLocX(),(int)bead.getLocY(),(int)bead.getLocX()+ConstantUtil.SIZE,(int)bead.getLocY()+ConstantUtil.SIZE);
    }

    @Override
    public String getState() {
        return "Disappear";
    }

    @Override
    public boolean OnMessage(Bead bead, Telegram msg) {
        return false;
    }

    //饿汉单例模式
    public static Bead_Disappear getInstance() {
        return bead_disappear;
    }

    private void setBeadLocY(Bead bead){
        bead.setLocY(bead.getId()/ConstantUtil.COUNT*ConstantUtil.SIZE+ConstantUtil.OFFSETY);
    }

    private void setBeadX(Bead bead){
        bead.setX(bead.getId()/ConstantUtil.COUNT);
    }

}
