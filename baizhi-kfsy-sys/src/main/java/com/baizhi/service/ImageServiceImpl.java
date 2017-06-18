package com.baizhi.service;

import com.baizhi.dao.ImageDao;
import com.baizhi.entity.Image;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MaXn on 2017/6/14.
 */
@Service("/imageService")
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Image> queryAll(Integer pageNum, Integer pageSize) {
        Page<Image> page = PageHelper.startPage(pageNum, pageSize);
        imageDao.selectAll();
        return page;
    }

    public void remove(String id) {
        imageDao.delete(id);
    }
}
