package com.baizhi.dao;

import com.baizhi.entity.Product;

import java.util.List;

/**
 * 与商品相关的
 * Created by MaXn on 2017/6/14.
 */
public interface ProductDao extends BaseDao<Product> {

    //根据药品名查询
    Product selectByName(String name);

    //根据类型id查询商品
    List<Product> selectByCategory(String id);

}
