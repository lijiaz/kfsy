package com.baizhi.ws;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.*;
/**
 * Created by MaXn on 2017/6/15.
 */
@Controller
@RequestMapping("/userServer")
public class UserWebService {

    @Resource
    private UserService userService;
    @Resource
    private Jedis jedis;


    //已有账户用户登录接口
    @RequestMapping(value = "/login/{phone}/{password}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> login(@PathVariable String phone,@PathVariable String password){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            User user = new User();
            user.setPhone(phone);
            user.setPassword(password);
            User u = userService.queryOneByPhone(user);
            rm.put("code","1");
            rm.put("message","登录成功");
            rm.put("currentUser",u);
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message",e.getMessage());
        }
        return rm;
    }


    //获取验证码接口
    @RequestMapping(value = "/temporaryLogin/{phone}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> temporaryLogin(@PathVariable String phone){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            //利用阿里大于生成短信验证码
            String url = "http://gw.api.taobao.com/router/rest";
            String appkey = "23756226";
            String secret = "1b57b101adbdb93bf7f12fb75174423e";
            TaobaoClient client = new DefaultTaobaoClient(url,appkey, secret);
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
            req.setSmsType("normal");
            req.setSmsFreeSignName("GKATE团队");
            Random random = new Random();   //生成随机激活码
            int nextInt = random.nextInt(999999);
            req.setSmsParamString("{\"code\":\""+nextInt+"\"}");
            req.setRecNum(phone);
            req.setSmsTemplateCode("SMS_63760793");
            client.execute(req);
            /*ValueOperations valueOperations = redisTemplate.opsForValue();
            valueOperations.set(phone,nextInt);*/
            jedis.set(phone,String.valueOf(nextInt));   //保存到redis中
            jedis.pexpire(phone,1800);  //设置失效时间

            rm.put("code","1");
            rm.put("message","获取验证成功");
            rm.put("msgcode",nextInt);
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","获取验证码失败");
        }
        return rm;
    }


    //验证手机验证码接口
    @RequestMapping(value = "/checkMsgcode/{phone}/{code}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> checkMsgcode(@PathVariable String phone,@PathVariable String code){
        Map<String, Object> rm = new HashMap<String, Object>();
        String oldcode = jedis.get(phone);
        if(code.equals(oldcode)){
            //生成临时用户
            User user = new User();
            user.setPhone(phone);
            user.setId(UUID.randomUUID().toString());
            rm.put("code","1");
            rm.put("message","验证码正确");
            rm.put("temporaryUser",user);
        }else{
            rm.put("code","-1");
            rm.put("message","验证码错误");
        }
        return rm;
    }

    //用户注册接口
    @RequestMapping(value = "/regist/{phone}/{code}/{password}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@PathVariable String phone,@PathVariable String code,@PathVariable String password){
        Map<String, Object> rm = new HashMap<String, Object>();
        String oldcode = jedis.get(phone);
        if(code.equals(oldcode)){
            User user = new User(null, phone, null, password, null, new Date(), "1", null, null);
            userService.add(user);
            rm.put("code","1");
            rm.put("message","注册成功");
            rm.put("currentUser",user);   //设置注册成功的当前用户
        }else{
            rm.put("code","-1");
            rm.put("message","验证码错误");
        }
        return rm;
    }

    //修改用户信息服务
    @RequestMapping(value = "/reviseMsg",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Object> reviseMsg(@RequestBody User user){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            userService.update(user);
            rm.put("code","1");
            rm.put("message","修改信息成功");
            rm.put("currentUser",userService.queryOne(user.getId()));
        }catch (Exception e){
            e.printStackTrace();
            rm.put("code","-1");
            rm.put("message","修改信息失败");
        }
        return rm;
    }

}
