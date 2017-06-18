package com.baizhi.controller;
import com.baizhi.entity.DataGrid;
import com.baizhi.entity.Image;
import com.baizhi.service.ImageService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
/**
 * Created by MaXn on 2017/6/14.
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    @Resource
    private ImageService imageService;

    @RequestMapping("/getAll")
    @ResponseBody
    public DataGrid<Image> getAll(Integer page,Integer rows){
        Page<Image> pages = imageService.queryAll(page, rows);
        DataGrid<Image> dg = new DataGrid<Image>();
        dg.setTotal(Integer.valueOf(String .valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

    @RequestMapping("/del")
    @ResponseBody
    public void del(String id){
        imageService.remove(id);
    }

}
