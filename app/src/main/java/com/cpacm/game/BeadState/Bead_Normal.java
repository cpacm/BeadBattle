package com.cpacm.game.BeadState;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.cpacm.game.Bead.Bead;
import com.cpacm.game.Bead.BeadManager;
import com.cpacm.game.BeadMessage.Telegram;
import com.cpacm.game.IEntity.IState;
import com.cpacm.game.util.ConstantUtil;

/**
 * Created by cpacm on 2014/10/25.
 */
public class Bead_Normal implements IState<Bead> {

    private static final Bead_Normal bead_Normal = new Bead_Normal();
    private Paint paint = new Paint();

    private Bead_Normal() {
    }

    @Override
    public void Enter(Bead bead) {

    }

    @Override
    public void Execute(Bead bead, Canvas canvas) {
       canvas.drawBitmap(bead.getBitmap(), bead.getLocX(), bead.getLocY(), paint);
    }

    @Override
    public void Exit(Bead entity) {

    }

    @Override
    public String getState() {
        return "normal";
    }

    @Override
    public boolean OnMessage(Bead bead, Telegram msg) {
        switch (msg.getMsg()) {
            case ConstantUtil.SELECTED: {
                bead.getBeadStateManager().ChangeState(Bead_Selected.getInstance());
                return true;
            }
            case ConstantUtil.DROPCOUNT: {
                bead.setDrop_offset(bead.getDrop_offset() + 1);
                return true;
            }
            case ConstantUtil.DROP: {
                bead.getBeadStateManager().ChangeState(Bead_Drop.getInstance());
                return true;
            }
        }
        return false;
    }

    //饿汉单例模式
    public static Bead_Normal getInstance() {
        return bead_Normal;
    }
}
