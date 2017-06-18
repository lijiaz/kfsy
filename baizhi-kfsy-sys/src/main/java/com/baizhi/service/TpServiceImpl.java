package com.baizhi.service;

import com.baizhi.dao.TpDao;
import com.baizhi.entity.Product;
import com.baizhi.entity.Tp;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MaXn on 2017/6/15.
 */
@Service("/tpService")
@Transactional
public class TpServiceImpl implements TpService {

    @Autowired
    private TpDao tpDao;

    public void add(Tp tp) {
        tpDao.insert(tp);
    }

    public void delete(Tp tp) {
        tpDao.del(tp);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Product> queryOne(Integer pageNum, Integer pageSize,String id) {
        Page<Product> page = PageHelper.startPage(pageNum, pageSize);
        tpDao.selectProductsByTheme(id);
        return page;
    }
}
