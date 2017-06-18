package com.baizhi.controller;

import com.baizhi.entity.DataGrid;
import com.baizhi.entity.Order;
import com.baizhi.service.OrderService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
/**
 * Created by MaXn on 2017/6/17.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    //分页查询所有订单
    @RequestMapping("/getAll")
    @ResponseBody
    public DataGrid<Order> getAll(Integer page,Integer rows){
        Page<Order> pages = orderService.queryAll(page,rows);
        System.out.println(pages);
        DataGrid<Order> dg = new DataGrid<Order>();
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

    @RequestMapping("/getAllBy")
    @ResponseBody
    public DataGrid<Order> getAllBy(Integer page, Integer rows, String name, String value){
        DataGrid<Order> dg = new DataGrid<Order>();
        Page<Order> pages = null;
        if("orderStatus".equals(name)){
            pages = orderService.queryByPayStatus(page,rows,value);
        }else{
            pages = orderService.queryByOrderStatus(page, rows, value);
        }
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

    //查询一个订单
    @RequestMapping("/getOne")
    @ResponseBody
    public Order getOne(String id){
        Order order = orderService.queryOne(id);
        return order;
    }


}
