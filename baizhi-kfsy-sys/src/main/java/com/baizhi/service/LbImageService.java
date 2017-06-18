package com.baizhi.service;

import com.baizhi.entity.LbImage;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 轮播图Service层接口
 * Created by MaXn on 2017/6/13.
 */
public interface LbImageService {

    //插入一个轮播图
    void add(LbImage lbImage);

    //删除一个轮播图
    void remove(String id);

    //修改轮播图状态
    void update(LbImage lbImage);

    LbImage queryOne(String id);

    //查询所有轮播图
    Page<LbImage> queryAll(Integer pageNum, Integer pageSize);

    //查询所有状态为on的轮播图
    List<LbImage> queryAllUse();

}
