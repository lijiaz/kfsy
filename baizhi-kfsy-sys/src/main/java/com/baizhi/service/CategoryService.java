package com.baizhi.service;
import com.baizhi.entity.Category;
import com.github.pagehelper.Page;

import java.util.List;

/**
 *与药品种类相关的service层接口
 * Created by MaXn on 2017/6/12.
 */
public interface CategoryService {

    //添加一个药品种类
    void add(Category category);

    //删除一个药品种类
    void remove(String id);

    //根据主键查询一个
    Category queryOne(String id);

    //更新一个种类
    void update(Category category);

    //分页查询所有药品种类
    Page<Category> queryAll(Integer pageNum, Integer pageSize);

    //不分页查询所有
    List<Category> queryAllCategory();

}
