package com.baizhi.service;

import com.baizhi.dao.ThemeDao;
import com.baizhi.entity.Theme;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by MaXn on 2017/6/12.
 */
@Service("themeService")
@Transactional
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeDao themeDao;

    public void add(MultipartFile file, String name, String description,String realPath) {

        File f = new File(realPath, "themeimg");
        if(!f.exists()){
            f.mkdirs();
        }
        String newFileName = UUID.randomUUID().toString() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            themeDao.insert(new Theme(UUID.randomUUID().toString(),name,"/themeimg/"+newFileName,description,null));
            file.transferTo(new File(f,newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(String id) {
        themeDao.delete(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Theme queryOne(String id) {
        return themeDao.selectOne(id);
    }


    public void update(Theme theme) {
        themeDao.update(theme);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Theme> queryAll(Integer pageNum, Integer pageSize) {
        Page<Theme> page = PageHelper.startPage(pageNum, pageSize);
        themeDao.selectAll();
        return page;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Theme> queryAllTheme() {
        return themeDao.selectAll();
    }
}
