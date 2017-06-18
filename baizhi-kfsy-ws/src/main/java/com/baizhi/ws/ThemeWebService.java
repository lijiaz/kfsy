package com.baizhi.ws;

import com.baizhi.entity.Theme;
import com.baizhi.service.ThemeService;
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
@RequestMapping("/themeServer")
public class ThemeWebService {

    @Resource
    private ThemeService themeService;

    //查询所有主题
    @RequestMapping(value = "/getAllTheme",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllLb(){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            List<Theme> themes = themeService.queryAllTheme();
            rm.put("code","1");
            rm.put("message","查询数据成功");
            rm.put("themes",themes);  //返回所有主题
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","查询数据失败");
        }
        return rm;
    }

    //查询所有主题
    @RequestMapping(value = "/getOneTheme/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getOneTheme(@PathVariable String id){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            Theme theme = themeService.queryOne(id);
            rm.put("code","1");
            rm.put("message","查询数据成功");
            rm.put("themes",theme);  //返回所有主题
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","查询数据失败");
        }
        return rm;
    }
}
