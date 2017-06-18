package com.baizhi.dao;
import com.baizhi.entity.Manager;
/**
 *
 * 管理员Dao层接口
 * Created by MaXn on 2017/6/12.
 */
public interface ManagerDao extends BaseDao<Manager>{

    //根据用户名查询一个
    Manager selectOneByUserName(String userName);

}
