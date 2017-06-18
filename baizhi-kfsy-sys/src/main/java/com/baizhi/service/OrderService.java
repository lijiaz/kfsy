package com.baizhi.service;

import com.baizhi.entity.Order;
import com.baizhi.entity.OrderItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 订单接口
 * Created by MaXn on 2017/6/16.
 */
public interface OrderService {

    //根据用户id查询该用户的所有订单
    List<Order> queryByUserId(String id);

    //根据订单支付状态查询
    Page<Order> queryByPayStatus(Integer pageNum, Integer pageSize, String payStatus);

    //根据订单状态查询
    Page<Order> queryByOrderStatus(Integer pageNum, Integer pageSize, String orderStatus);

    //分页查询所有订单
    Page<Order> queryAll(Integer pageNum, Integer pageSize);

    //添加一个订单
    void add(Order order,List<OrderItem> orderItems);

    //修改订单(订单状态、支付状态、备注)
    void update(Order order);

    //根据id查询一个订单
    Order queryOne(String id);




}
