package com.cpacm.game.IEntity;

import android.graphics.Canvas;

import com.cpacm.game.BeadMessage.Telegram;

/**
 * Created by cpacm on 2014/10/9.
 */
public interface IEntity {
    //为每一个实体设定id
    void setId(int id);
    //消息发送器
    boolean HandleMessage(Telegram tgm);
    //更新
    void Update(Canvas canvas);


}
