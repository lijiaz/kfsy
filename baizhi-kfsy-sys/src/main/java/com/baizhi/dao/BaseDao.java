package com.baizhi.dao;
import java.util.List;
/**
 *
 * 通用 dao 层接口
 * Created by MaXn on 2017/6/12.
 */
public interface BaseDao<T> {

    //根据主键查询一个
    T selectOne(String id);

    //插入一个
    void insert(T t);

    //删除一个
    void delete(String id);

    //更新一个
    void update(T t);

    //查询所有
    List<T> selectAll();

}
