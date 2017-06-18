package com.baizhi.service;

import com.baizhi.dao.CategoryDao;
import com.baizhi.entity.Category;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

/**
 * Created by MaXn on 2017/6/12.
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public void add(Category category) {
        category.setId(UUID.randomUUID().toString());
        categoryDao.insert(category);
    }

    public void remove(String id) {
        categoryDao.delete(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Category queryOne(String id) {
        return categoryDao.selectOne(id);
    }

    public void update(Category category) {
        categoryDao.update(category);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Category> queryAll(Integer pageNum, Integer pageSize) {
        Page<Category> page = PageHelper.startPage(pageNum, pageSize);
        categoryDao.selectAll();
        return page;
    }


    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Category> queryAllCategory() {
        return categoryDao.selectAll();
    }


}
