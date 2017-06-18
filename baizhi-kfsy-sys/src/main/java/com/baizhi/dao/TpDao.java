package com.baizhi.dao;
import com.baizhi.entity.Product;
import com.baizhi.entity.Tp;

import java.util.List;

/**
 * Created by MaXn on 2017/6/14.
 */
public interface TpDao extends BaseDao<Tp>{

   //根据主题查询所有商品
    List<Product> selectProductsByTheme(String id);

    //删除一条数据
    void del(Tp tp);

}
