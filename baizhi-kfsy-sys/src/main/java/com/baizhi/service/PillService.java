package com.baizhi.service;

import com.baizhi.entity.Medicine;
import com.baizhi.entity.Product;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 药品相关的service层接口
 * Created by MaXn on 2017/6/14.
 */
public interface PillService {

    //根据药品名查询
    Product queryProductByName(String name);


    //查询一个药品
    Product queryOneProduct(String id);

    //分页查询所有
    Page<Product> queryAllProduct(Integer pageNum, Integer pageSize);

    //不分页查询所有
    List<Product> queryAll();

    //查询一个药品详情
    Medicine queryOneMedicine(String id);

    //添加一个药品详情
    void add(Product product,Medicine medicine);

    //删除一个药品
    void remove(String id);

}
