package com.cpacm.game.BeadMessage;

import android.util.Log;

import com.cpacm.game.Bead.Bead;
import com.cpacm.game.Bead.BeadManager;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by cpacm on 2014/10/23.
 */
public class MessageDispatcher {
    private static final String TAG = "cpacm";

    private Set<Telegram> PriorityQ = new TreeSet<Telegram>();

    private static MessageDispatcher messageDispatcher = new MessageDispatcher();

    private MessageDispatcher(){}

    public static MessageDispatcher getInstance(){
        return messageDispatcher;
    }

    public void DispatchMessage(double delay,int sender,int receiver,int msg){
        Bead bead = BeadManager.getInstance().GetEntityFromId(receiver);
        Telegram telegram = new Telegram(delay,sender,receiver,msg);
        if(delay<=0.0){
            //发送
            Discharge(bead,telegram);
        }
        //否则根据延迟时间来发送时间
        else{
            telegram.setPatchTime(System.currentTimeMillis()+delay);
            PriorityQ.add(telegram);
        }
    }

    public void Discharge(Bead bead,Telegram telegram){
        bead.HandleMessage(telegram);
    }

    public void DispatchDelayedMessages(){
        //首先获得时间
        long time = System.currentTimeMillis();
        //查看队列中是否有消息需要发送
        Iterator i = PriorityQ.iterator();//先迭代出来
        synchronized(PriorityQ) {
            while (i.hasNext()) {//遍历
                Telegram t = (Telegram) i.next();
                if (t.getPatchTime() < time && t.getPatchTime() > 0) {
                    Bead b = BeadManager.getInstance().GetEntityFromId(t.getReceiver());
                    Discharge(b, t);
                    i.remove();
                    Log.d(TAG, "成功发送延时消息，并删除——" + t.getMsg());
                } else {
                    break;
                }
            }
        }
    }

}
