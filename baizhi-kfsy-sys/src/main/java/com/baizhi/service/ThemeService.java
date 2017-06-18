package com.baizhi.service;
import com.baizhi.entity.Theme;
import com.github.pagehelper.Page;

import java.util.List;

/**
 *与药品种类相关的service层接口
 * Created by MaXn on 2017/6/12.
 */
public interface ThemeService {

    //添加一个药品主题
    void add(Theme theme);

    //删除一个药品主题
    void remove(String id);

    //根据主键查询一个
    Theme queryOne(String id);

    //更新一个主题
    void update(Theme theme);

    //分页查询所有药品主题
    Page<Theme> queryAll(Integer pageNum, Integer pageSize);

    //不使用分页查询所有药品主题
    List<Theme> queryAllTheme();

}
