package com.baizhi.controller;
import com.baizhi.entity.DataGrid;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
/**
 *
 * 后台系统操作user的Controller
 * Created by MaXn on 2017/6/12.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/getAll")
    @ResponseBody
    public DataGrid<User> getAll(Integer page,Integer rows){
        Page<User> pages = userService.queryAll(page,rows);
        DataGrid<User> dg = new DataGrid<User>();
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

    @RequestMapping("/getAllBy")
    @ResponseBody
    public DataGrid<User> getAllBy(Integer page,Integer rows,String name,String value){
        DataGrid<User> dg = new DataGrid<User>();
        Page<User> pages = null;
        if("nickName".equals(name)){
            pages = userService.queryByNickName(page, rows, value);
        }else{
            pages = userService.queryByPhone(page, rows, value);
        }
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

}
