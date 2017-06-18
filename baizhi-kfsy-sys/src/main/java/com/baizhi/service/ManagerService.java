package com.baizhi.service;

import com.baizhi.entity.Manager;

/**
 * 管理员service层接口
 * Created by MaXn on 2017/6/12.
 */
public interface ManagerService {

    //添加一个管理员
    void add(Manager manager);

    //根据用户名查询一个
    Manager queryOneByUserName(Manager manager);

}
