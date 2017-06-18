package com.baizhi.service;

import com.baizhi.entity.User;
import com.github.pagehelper.Page;

import java.util.Date;
import java.util.List;

/**
 * 用户相关的Serice层接口
 * Created by MaXn on 2017/6/12.
 */
public interface UserService {

    //根据手机号查询一个
    User queryOneByPhone(User user);

    //添加一个用户
    void add(User user);

    //删除一个用户
    void remove(String id);

    //根据主键查询一个
    User queryOne(String id);

    //更新一个用户
    void update(User user);

    //查询所有用户
    Page<User> queryAll(Integer pageNum, Integer pageSize);

    //根据手机号模糊查询
    Page<User> queryByNickName(Integer pageNum, Integer pageSize,String nickName);

    //根据昵称模糊查询
    Page<User> queryByPhone(Integer pageNum, Integer pageSize,String phone);

    //查询某段时间注册时用户
    List<User> queryCount(Date currentDate,Date beforeDate);

}
