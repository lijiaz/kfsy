package com.baizhi.service;

import com.baizhi.dao.OrderDao;
import com.baizhi.dao.OrderItemDao;
import com.baizhi.entity.Order;
import com.baizhi.entity.OrderItem;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by MaXn on 2017/6/16.
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Order> queryByUserId(String id) {
        return orderDao.selectByUserId(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Order> queryByPayStatus(Integer pageNum, Integer pageSize, String payStatus) {
        Page<Order> page = PageHelper.startPage(pageNum, pageSize);
        orderDao.selectByPayStatus(payStatus);
        return page;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Order> queryByOrderStatus(Integer pageNum, Integer pageSize, String orderStatus) {
        Page<Order> page = PageHelper.startPage(pageNum, pageSize);
        orderDao.selectByOrderStatus(orderStatus);
        return page;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Order> queryAll(Integer pageNum, Integer pageSize) {
        Page<Order> page = PageHelper.startPage(pageNum, pageSize);
        orderDao.selectAll();
        return page;
    }


    //添加订单，并添加购物项
    public void add(Order order,List<OrderItem> orderItems) {
        String orderId = UUID.randomUUID().toString();
        order.setId(orderId);
        Double totalPrice = 0.0;
        order.setOrderNum("20176162140");
        order.setCreateDate(new Date());
        order.setPayStatus("false");
        order.setOrderStatus("false");
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getPrice();
        }
        order.setTotalPrice(totalPrice);
        for (OrderItem orderItem : orderItems) {
            orderItem.setId(UUID.randomUUID().toString());
            orderItem.setOrder(order);
            orderItemDao.insert(orderItem);
        }
        orderDao.insert(order);
    }


    public void update(Order order) {
        orderDao.update(order);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Order queryOne(String id) {
        return orderDao.selectOne(id);
    }
}
