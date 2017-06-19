package com.baizhi.controller;
import com.baizhi.entity.DataGrid;
import com.baizhi.entity.LbImage;
import com.baizhi.service.LbImageService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MaXn on 2017/6/13.
 */
@Controller
@RequestMapping("/lbimage")
public class LbImageController {

    @Autowired
    private LbImageService lbImageService;

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(MultipartFile file, String name, String status, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        lbImageService.add(file,name,status,realPath);
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public DataGrid<LbImage> getAll(Integer page, Integer rows){
        Page<LbImage> pages = lbImageService.queryAll(page, rows);
        DataGrid<LbImage> dg = new DataGrid<LbImage>();
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

    //删除一个轮播图
    @RequestMapping("/del")
    @ResponseBody
    public void del(String id){
        lbImageService.remove(id);
    }

    //获取一个轮播图
    @RequestMapping("/getOne")
    @ResponseBody
    public LbImage getOne(String id){
        return lbImageService.queryOne(id);
    }

    //更新一个轮播图
    @RequestMapping("/update")
    @ResponseBody
    public void update(LbImage lbImage){
        lbImageService.update(lbImage);
    }

}
