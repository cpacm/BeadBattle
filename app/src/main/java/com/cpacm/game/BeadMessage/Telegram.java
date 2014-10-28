package com.cpacm.game.BeadMessage;

/**
 * Created by cpacm on 2014/10/22.
 */
public class Telegram implements Comparable<Telegram> {

    private int sender;
    private int receiver;
    private double patchTime;
    private int msg;

    @Override
    public int compareTo(Telegram t) {
        if(this.getPatchTime()<t.getPatchTime())
            return -1;
        else
            return 1;
    }


    public Telegram(double patchTime,int sender, int receiver, int msg) {
        this.sender = sender;
        this.receiver = receiver;
        this.patchTime = patchTime;
        this.msg = msg;
    }

    public void setPatchTime(double patchTime) {
        this.patchTime = patchTime;
    }

    public double getPatchTime() {
        return patchTime;
    }

    public int getReceiver() {
        return receiver;
    }

    public int getMsg() {
        return msg;
    }
}
