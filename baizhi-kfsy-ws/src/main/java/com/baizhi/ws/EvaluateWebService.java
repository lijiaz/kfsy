package com.baizhi.ws;

import com.baizhi.entity.Evaluate;
import com.baizhi.entity.Order;
import com.baizhi.entity.User;
import com.baizhi.service.EvaluateService;
import com.baizhi.service.OrderService;
import com.baizhi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MaXn on 2017/6/16.
 */
@Controller
@RequestMapping("/evaluateServer")
public class EvaluateWebService {

    @Resource
    private EvaluateService evaluateService;
    @Resource
    private UserService userService;
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/getAllEvaluate",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllEvaluate(){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            List<Evaluate> evaluates = evaluateService.queryAll();
            rm.put("code","1");
            rm.put("message","获取数据成功");
            rm.put("evaluates",evaluates);
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","获取数据失败");
        }
        return rm;
    }

    @RequestMapping(value = "/addEvaluate/{orderId}/{userId}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addEvaluate(@PathVariable String orderId, @PathVariable String userId, @RequestBody Evaluate evaluate){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            User user = userService.queryOne(userId);
            Order order = orderService.queryOne(orderId);
            evaluate.setUser(user);
            evaluate.setOrder(order);
            evaluateService.add(evaluate);
            rm.put("code","1");
            rm.put("message","操作成功");
        }catch (Exception e){
            e.printStackTrace();
            rm.put("code","-1");
            rm.put("message","操作失败");
        }
        return rm;
    }


}
