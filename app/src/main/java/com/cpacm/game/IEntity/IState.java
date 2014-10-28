package com.cpacm.game.IEntity;

import android.graphics.Canvas;

import com.cpacm.game.BeadMessage.Telegram;

/**
 * Created by cpacm on 2014/10/20.
 */
public interface IState<T>{
    //进入某状态时调用，可以进行一些参数的设置
    void Enter(T entity);
    //运行时调用，在主线程中不断调用
    void Execute(T entity,Canvas canvas);
    //退出该状态时调用
    void Exit(T entity);
    //返回状态
    String getState();
    //接受消息并处理
    boolean OnMessage(T entity,Telegram msg);
}
