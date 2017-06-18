package com.baizhi.controller;

import com.baizhi.entity.DataGrid;
import com.baizhi.entity.Product;
import com.baizhi.entity.Theme;
import com.baizhi.entity.Tp;
import com.baizhi.service.ThemeService;
import com.baizhi.service.TpService;
import com.github.pagehelper.Page;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 药品种类Controller
 * Created by MaXn on 2017/6/12.
 */
@RequestMapping("/theme")
@Controller
public class ThemeController {

    @Resource
    private ThemeService themeService;
    @Resource
    private TpService tpService;

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(MultipartFile file,String name,String description,HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File f = new File(realPath, "themeimg");
        if(!f.exists()){
           f.mkdirs();
        }
        String newFileName = UUID.randomUUID().toString() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            file.transferTo(new File(f,newFileName));
            themeService.add(new Theme(null,name,"/themeimg/"+newFileName,description,null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //分页查询所有药品主题
    @RequestMapping("/getAll")
    @ResponseBody
    public DataGrid<Theme> getAll(Integer page,Integer rows){
        Page<Theme> pages = themeService.queryAll(page, rows);
        DataGrid<Theme> dg = new DataGrid<Theme>();
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

    //不适用分页查询所有主题
    @ResponseBody
    @RequestMapping("/getAllTheme")
    public List<Theme> getAllTheme(){
        List<Theme> themes = themeService.queryAllTheme();
        return themes;
    }

    @RequestMapping("/del")
    @ResponseBody
    public void del(String id){
        themeService.remove(id);
    }


    //获取某个主题下的所有商品
    @ResponseBody
    @RequestMapping("/getAllProduct")
    public DataGrid<Product> getAllProduct(Integer page,Integer rows,String id){
        Page<Product> pages = tpService.queryOne(page, rows, id);
        DataGrid<Product> dg = new DataGrid<Product>();
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

    @RequestMapping("/add")
    @ResponseBody
    public void add(String themeId,String MedicineId){
        tpService.add(new Tp(MedicineId,themeId));
    }

}
