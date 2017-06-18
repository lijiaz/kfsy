package com.baizhi.dao;
import com.baizhi.entity.Evaluate;

import java.util.List;

/**
 *用户评价相关的dao层接口
 * Created by MaXn on 2017/6/13.
 */
public interface EvaluateDao extends BaseDao<Evaluate>{

    //根据评论内容模糊查询
    List<Evaluate> selectByContent(String content);

    //根据评价星级模糊查询
    List<Evaluate> selectByStar(String Star);
}
