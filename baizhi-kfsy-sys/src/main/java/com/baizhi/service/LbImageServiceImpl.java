package com.baizhi.service;

import com.baizhi.dao.LbImageDao;
import com.baizhi.entity.LbImage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by MaXn on 2017/6/13.
 */
@Service("lbImageService")
@Transactional
public class LbImageServiceImpl implements LbImageService {

    @Autowired
    private LbImageDao lbImageDao;

    public void add(LbImage lbImage) {
        lbImage.setId(UUID.randomUUID().toString());
        lbImageDao.insert(lbImage);
    }

    public void remove(String id) {
        lbImageDao.delete(id);
    }

    public void update(LbImage lbImage) {
        lbImageDao.update(lbImage);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public LbImage queryOne(String id) {
        return lbImageDao.selectOne(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<LbImage> queryAll(Integer pageNum, Integer pageSize) {
        Page<LbImage> page = PageHelper.startPage(pageNum, pageSize);
        lbImageDao.selectAll();
        return page;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<LbImage> queryAllUse() {
        return lbImageDao.selectAllUse();
    }
}
