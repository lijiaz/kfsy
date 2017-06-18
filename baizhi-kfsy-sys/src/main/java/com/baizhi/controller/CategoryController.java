package com.baizhi.controller;

import com.baizhi.entity.Category;
import com.baizhi.entity.DataGrid;
import com.baizhi.service.CategoryService;
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
@RequestMapping("/category")
@Controller
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping("/upload")
    @ResponseBody
    public Category upload(MultipartFile file,HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File f = new File(realPath, "categoryimg");
        if(!f.exists()){
           f.mkdirs();
        }
        String newFileName = UUID.randomUUID().toString() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            file.transferTo(new File(f,newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Category category = new Category(null, null, "/categoryimg/" + newFileName, null);
        return category;
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(String name,String url){
        System.out.println(url);
        categoryService.add(new Category(null,name,url,null));
    }

    //分页查询所有
    @RequestMapping("/getAll")
    @ResponseBody
    public DataGrid<Category> getAll(Integer page,Integer rows){
        Page<Category> pages = categoryService.queryAll(page, rows);
        DataGrid<Category> dg = new DataGrid<Category>();
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

    //不分页查询所有
    @RequestMapping("/getAllCategory")
    @ResponseBody
    public List<Category> getAllCategory(){
        return categoryService.queryAllCategory();
    }


    @RequestMapping("/del")
    @ResponseBody
    public void del(String id){
        categoryService.remove(id);
    }

}
