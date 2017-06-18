package com.baizhi.service;
import com.baizhi.entity.Product;
import com.baizhi.entity.Tp;
import com.github.pagehelper.Page;
/**
 * Created by MaXn on 2017/6/15.
 */
public interface TpService {

    public void add(Tp tp);

    public void delete(Tp tp);

    public Page<Product> queryOne(Integer pageNum, Integer pageSize,String id);
}
