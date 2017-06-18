package com.baizhi.dao;
import com.baizhi.entity.LbImage;

import java.util.List;

/**
 * 轮播图 dao层接口
 * Created by MaXn on 2017/6/13.
 */
public interface LbImageDao extends BaseDao<LbImage> {

    //查询所有状态为on的轮播图
    List<LbImage> selectAllUse();

}
