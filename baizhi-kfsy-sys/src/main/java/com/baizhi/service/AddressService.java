package com.baizhi.service;
import com.baizhi.entity.Address;
import java.util.List;
/**
 * 用户收货地址service层接口
 * Created by MaXn on 2017/6/13.
 */
public interface AddressService {

    //插入一个地址
    void add(Address address);

    //根据主键查询一个地址
    Address queryOne(String id);

    //删除一个地址
    void remove(String id);

    //更新一个地址
    void update(Address address);

    //查询所有用户的所有地址
    List<Address> queryAll();

    //根据用户的主键查询该用户的所有地址
    List<Address> queryAllByUserId(String id);

}
