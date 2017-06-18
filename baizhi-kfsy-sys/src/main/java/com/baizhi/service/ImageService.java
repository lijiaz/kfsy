package com.baizhi.service;
import com.baizhi.entity.Image;
import com.github.pagehelper.Page;
/**
 * 药品详情图片
 * Created by MaXn on 2017/6/14.
 */
public interface ImageService {

    //查询所有商品图片
    Page<Image> queryAll(Integer pageNum, Integer pageSize);

    //删除一个图片
    void remove(String id);

}
