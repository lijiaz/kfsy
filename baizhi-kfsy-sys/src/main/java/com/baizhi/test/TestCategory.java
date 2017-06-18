package com.baizhi.test;

import com.baizhi.dao.OrderItemDao;
import com.baizhi.entity.Order;
import com.baizhi.service.CategoryService;
import com.baizhi.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by MaXn on 2017/6/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-basic.xml")
public class TestCategory {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemDao orderItemDao;

    @Test
    public void test(){
        /*Category category = categoryService.queryOne("a1ed4b25-a618-403c-a072-46c1d4cc8a60");
        System.out.println(category);*/
        Order order = orderService.queryOne("231e9f1d-741f-485a-8659-6dcc382404c2");
        System.out.println(order);
        /*List<OrderItem> orderItems = orderItemDao.selectByOrderId("231e9f1d-741f-485a-8659-6dcc382404c2");
        System.out.println(orderItems);*/
    }
}
