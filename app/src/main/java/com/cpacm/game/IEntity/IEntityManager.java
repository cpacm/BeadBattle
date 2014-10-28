package com.cpacm.game.IEntity;

/**
 * Created by cpacm on 2014/10/23.
 */
public interface IEntityManager<T> {
    //为一个新的实体类注册
    void RegisterEntity(T entity);
    //消去一个已经注册过的实体类
    void RemoveEntity(T entity);
    //根据id返回一个实体类
    T GetEntityFromId(int id);
}
