package com.baizhi.dao;


import com.baizhi.entity.Order;

import java.util.List;

/**
 * 订单相关的Dao层接口
 * Created by MaXn on 2017/6/14.
 */
public interface OrderDao extends BaseDao<Order>{

    //根据用户id查询该用户的所有订单
    List<Order> selectByUserId(String id);

    //根据订单支付状态查询
    List<Order> selectByPayStatus(String payStatus);

    //根据订单状态查询
    List<Order> selectByOrderStatus(String orderStatus);

}
