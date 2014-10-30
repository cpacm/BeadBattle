package com.cpacm.game.thread;

import android.util.Log;

import com.cpacm.game.Bead.BeadManager;
import com.cpacm.game.BeadMessage.MessageDispatcher;
import com.cpacm.game.util.ConstantUtil;
import com.cpacm.game.views.BeadGroupView;

/**
 * 绘制珠子的线程
 * Created by cpacm on 2014/10/11.
 */
public class BeadGroupThread extends Thread {

    private BeadGroupView view;
    private boolean isRun = false;

    public BeadGroupThread(BeadGroupView view){
        isRun = true;
        this.view = view;
    }

    @Override
    public void run() {
        long preTime,newTime;
        while(isRun){
            preTime = System.currentTimeMillis();
            view.OnDraw();
            MessageDispatcher.getInstance().DispatchDelayedMessages();
            newTime = System.currentTimeMillis();
            try {
                if(newTime-preTime<ConstantUtil.RATE)
                    sleep(ConstantUtil.RATE-newTime+preTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newTime = System.currentTimeMillis();
            Log.d("TEST", "耗时:" + (newTime - preTime));
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
