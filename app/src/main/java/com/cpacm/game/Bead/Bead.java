package com.cpacm.game.Bead;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.cpacm.game.IEntity.Entity;
import com.cpacm.game.BeadMessage.Telegram;
import com.cpacm.game.util.ConstantUtil;

/**
 * Created by cpacm on 2014/10/22.
 */
public class Bead extends Entity {

    private static final String TAG = "cpacm";
    //规定珠子的数量
    private static final int MAX = ConstantUtil.MAX;
    //珠子的类型
    private BeadType type;
    //珠子的图像资源
    private Bitmap bitmap;
    //动画的偏移量
    private float offset = 0;


    private BeadStateManager beadStateManager;

    public Bead(int id){
        setId(id);
        beadStateManager = new BeadStateManager(this);
    }

    public Bead(int id,BeadType type){
        setId(id);
        beadStateManager = new BeadStateManager(this,type);
    }

    @Override
    public void setId(int id) {
        if (id>=0&&id<MAX){
            this.id = id;
        }
        else{
            Log.d(TAG,"id设置失败");
        }
    }

    @Override
    public boolean HandleMessage(Telegram tgm) {
        return beadStateManager.HandleMessage(tgm);
    }

    @Override
    public void Update(Canvas canvas) {
        beadStateManager.Update(canvas);
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public void setType(BeadType type) {
        this.type = type;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public BeadStateManager getBeadStateManager() {
        return beadStateManager;
    }

    public void setBeadStateManager(BeadStateManager beadStateManager) {
        this.beadStateManager = beadStateManager;
    }

    public BeadType getType() {
        return type;
    }
}
