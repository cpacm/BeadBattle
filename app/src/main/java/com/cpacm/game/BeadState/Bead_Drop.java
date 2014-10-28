package com.cpacm.game.BeadState;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.cpacm.game.Bead.Bead;
import com.cpacm.game.Bead.BeadManager;
import com.cpacm.game.BeadMessage.Telegram;
import com.cpacm.game.IEntity.IState;
import com.cpacm.game.util.ConstantUtil;

/**
 * Created by cpacm on 2014/10/27.
 */
public class Bead_Drop implements IState<Bead> {

    private static Bead_Drop bead_drop = new Bead_Drop();
    private static final int SPAN = 15;
    private Paint paint;

    private Bead_Drop(){paint = new Paint();}

    public static Bead_Drop getInstance(){
        return bead_drop;
    }
    @Override
    public void Enter(Bead bead) {
        bead.setId((int)bead.getOffset() * ConstantUtil.COUNT + bead.getId());
        bead.setX(bead.getId() / ConstantUtil.COUNT);
        bead.setOffset(bead.getOffset() * ConstantUtil.SIZE + bead.getLocY());
    }

    @Override
    public void Execute(Bead bead, Canvas canvas) {
        if(bead.getOffset()>bead.getLocY()) {
            bead.setLocY(bead.getLocY()+SPAN);
            canvas.drawBitmap(bead.getBitmap(),bead.getLocX(), bead.getLocY(),paint);
        }
        else{
            bead.setLocY(bead.getOffset());
            canvas.drawBitmap(bead.getBitmap(),bead.getLocX(), bead.getLocY(),paint);
            bead.getBeadStateManager().ChangeState(Bead_Normal.getInstance());
        }
    }

    @Override
    public void Exit(Bead bead) {
        bead.setOffset(0);
    }

    @Override
    public String getState() {
        return "drop";
    }

    @Override
    public boolean OnMessage(Bead bead, Telegram msg) {
        return false;
    }
}
