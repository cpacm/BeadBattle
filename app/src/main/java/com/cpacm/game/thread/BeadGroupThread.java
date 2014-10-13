package com.cpacm.game.thread;

import com.cpacm.game.Bead.BeadStatus;
import com.cpacm.game.util.ConstantUtil;
import com.cpacm.game.views.BeadGroupView;

/**
 * Created by cpacm on 2014/10/11.
 */
public class BeadGroupThread extends Thread {

    private BeadGroupView view;

    private boolean isRun = false;

    private BeadStatus beadStatus;

    public BeadGroupThread(BeadGroupView view){
        this.view = view;
        isRun = true;
        beadStatus = new BeadStatus(view.getBeadArray());
    }

    @Override
    public void run() {
        while(isRun){
            beadStatus.StatusChange();
            view.OnDraw();
            try {
                sleep(ConstantUtil.RATE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        super.run();
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean isRun) {
        this.isRun = isRun;
    }

}
