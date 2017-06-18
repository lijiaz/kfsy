package com.baizhi.ws;

import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MaXn on 2017/6/15.
 */
@Controller
@RequestMapping("/categoryServer")
public class CategoryWebService {

    @Resource
    private CategoryService categoryService;

    //查询所有主题
    @RequestMapping(value = "/getAllCategory",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllCategory(){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            List<Category> categories = categoryService.queryAllCategory();
            rm.put("code","1");
            rm.put("message","查询数据成功");
            rm.put("categories",categories);  //返回所有类别
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","查询数据失败");
        }
        return rm;
    }

    //查询所有主题
    @RequestMapping(value = "/getOneCategory/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getOneCategory(@PathVariable String id){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            Category category = categoryService.queryOne(id);
            rm.put("code","1");
            rm.put("message","查询数据成功");
            rm.put("category",category);  //返回一个类别
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","查询数据失败");
        }
        return rm;
    }
}
