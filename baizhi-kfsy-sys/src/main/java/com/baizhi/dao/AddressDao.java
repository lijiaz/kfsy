package com.baizhi.dao;

import com.baizhi.entity.Address;

import java.util.List;

/**
 * 用户收获地址dao层接口
 * Created by MaXn on 2017/6/13.
 */
public interface AddressDao extends BaseDao<Address> {


    //查询某个用户的所有地址
    List<Address> selectAllByUserId(String id);


}
