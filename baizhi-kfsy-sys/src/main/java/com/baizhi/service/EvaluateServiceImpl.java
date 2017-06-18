package com.baizhi.service;

import com.baizhi.dao.EvaluateDao;
import com.baizhi.entity.Evaluate;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by MaXn on 2017/6/13.
 */
@Service("/evaluateService")
@Transactional
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateDao evaluateDao;

    public void add(Evaluate evaluate) {
        evaluate.setId(UUID.randomUUID().toString());
        evaluate.setCreateDate(new Date());
        evaluateDao.insert(evaluate);
    }


    //分页查询所有用户评价
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Evaluate> queryAllEval(Integer pageNum, Integer pageSize) {
        Page<Evaluate> page = PageHelper.startPage(pageNum, pageSize);
        evaluateDao.selectAll();
        return page;
    }

    //不使用分页查询
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Evaluate> queryAll() {
        return evaluateDao.selectAll();
    }


    //根据评价内容模糊查询
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Evaluate> queryByContent(Integer pageNum, Integer pageSize,String content) {
        Page<Evaluate> page = PageHelper.startPage(pageNum, pageSize);
        evaluateDao.selectByContent(content);
        return page;
    }

    //根据星级模糊查询
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Evaluate> queryByStar(Integer pageNum, Integer pageSize,String Star) {
        Page<Evaluate> page = PageHelper.startPage(pageNum, pageSize);
        evaluateDao.selectByStar(Star);
        return page;
    }
}
