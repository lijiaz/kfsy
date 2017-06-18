package com.baizhi.service;

import com.baizhi.entity.Evaluate;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 用户评价service层接口
 * Created by MaXn on 2017/6/13.
 */
public interface EvaluateService {

    //添加一条评价
    void add(Evaluate evaluate);

    //分页查询所有的评价
    Page<Evaluate> queryAllEval(Integer pageNum, Integer pageSize);

    //不使用分页查询所有
    List<Evaluate> queryAll();

    //根据评论内容模糊查询
    Page<Evaluate> queryByContent(Integer pageNum, Integer pageSize,String content);

    //根据评价星级模糊查询
    Page<Evaluate> queryByStar(Integer pageNum, Integer pageSize,String Star);

}
