package com.cpacm.game.Bead;

import com.cpacm.game.IEntity.IEntityManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 实体管理类
 * Created by cpacm on 2014/10/23.
 */
public class BeadManager implements IEntityManager<Bead> {
    private static final String TAG = "cpacm";

    private static final BeadManager beadManager = new BeadManager();

    private Map<Integer,Bead> beadMap = new HashMap<Integer, Bead>();

    private BeadManager(){}

    private int disappearCount = 0;
    private int dropCount = 0;

    @Override
    public void RegisterEntity(Bead bead) {
        beadMap.put(bead.getId(),bead);
    }

    @Override
    public void RemoveEntity(Bead bead) {
        beadMap.remove(bead.getId());
    }

    @Override
    public Bead GetEntityFromId(int id) {
        return beadMap.get(id);
    }

    public static BeadManager getInstance(){
        return beadManager;
    }

    public void addDisappearCount(int disappearCount) {
        this.disappearCount += disappearCount;
    }
    public void addDropCount(int dropCount) {
        this.dropCount += dropCount;
    }

    public boolean isNext(){
        if(disappearCount == 0 && dropCount == 0){
            return true;
        }
        return false;
    }
}
