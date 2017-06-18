package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 *与系统相关的controller
 * Created by MaXn on 2017/6/17.
 */
@RequestMapping("/sys")
@Controller
public class SystemController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/getCount")
    public Map<String,List<String>> getCount(){
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Calendar calendar = Calendar.getInstance();

        Date currentDate = calendar.getTime();  //当前时间
        List<String> category = new ArrayList<String>();
        List<String> count = new ArrayList<String>();

        category.add(0,"10天前");
        category.add(1,"7天前");
        category.add(2,"5天前");
        category.add(3,"3天前");
        map.put("category",category);
        //十天前
        calendar.add(Calendar.DATE,-10);
        Date ten = calendar.getTime();
        List<User> tens = userService.queryCount(currentDate, ten);//十天前的人
        count.add(0,tens.size()+"");
        //七天前
        calendar.add(Calendar.DATE,3);
        Date seven = calendar.getTime();
        List<User> sevens = userService.queryCount(currentDate, seven);//十天前的人
        count.add(1,sevens.size()+"");

        //五天前
        calendar.add(Calendar.DATE,5);
        Date five = calendar.getTime();
        List<User> fives = userService.queryCount(currentDate, five);//十天前的人
        count.add(2,fives.size()+"");

        //三天前
        calendar.add(Calendar.DATE,7);
        Date three = calendar.getTime();
        List<User> threes = userService.queryCount(currentDate, three);//十天前的人
        count.add(3,threes.size()+"");
        map.put("count",count);
        return map;

    }

}
