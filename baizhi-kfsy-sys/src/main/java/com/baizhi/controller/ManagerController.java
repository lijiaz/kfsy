package com.baizhi.controller;

import com.baizhi.entity.Manager;
import com.baizhi.service.ManagerService;
import com.baizhi.util.EncodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 与管理员相关的Controller
 * Created by MaXn on 2017/6/12.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @RequestMapping("/login")
    public String login(Manager manager,String imageCode,HttpSession session){
        String code = (String)session.getAttribute("imageCode");
        if(imageCode.equalsIgnoreCase(code)){
            try{
                Manager m = managerService.queryOneByUserName(manager);
                session.setAttribute("currentManager",m);
                return "redirect:/back/main/main.jsp";
            }catch (Exception e){
                String msg = e.getMessage();
                return "redirect:/back/login?msg="+ EncodeUtil.encode(msg);
            }
        }else{
            return "redirect:/back/login?msg="+ EncodeUtil.encode("验证码错误");
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("currentManager");
        return "redirect:/back/login.jsp";
    }


}
