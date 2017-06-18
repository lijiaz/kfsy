package com.baizhi.ws;

import com.baizhi.entity.Address;
import com.baizhi.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MaXn on 2017/6/15.
 */
@Controller
@RequestMapping("/addressServer")
public class UserAddressWebService {

    @Resource
    private AddressService addressService;


    //删除一个已有的地址
    @RequestMapping(value = "/deleteAddress/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteAddress(@PathVariable String id){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            addressService.remove(id);
            rm.put("code","1");
            rm.put("message","删除成功");
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","删除失败");
        }
        return rm;
    }

    //添加一个地址
    @RequestMapping(value = "/addAddress",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addAddress(@RequestBody Address address){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            addressService.add(address);
            List<Address> addresses = addressService.queryAllByUserId(address.getUser().getId());
            rm.put("code","1");
            rm.put("message","添加地址成功");
            rm.put("addresses",addresses);  //返回当前用户的所有地址
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","添加地址失败");
        }
        return rm;
    }

    //查询某个用户的所有
    @RequestMapping(value = "/getUserAddress/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getUserAddress(@PathVariable String id){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            List<Address> addresses = addressService.queryAllByUserId(id);
            rm.put("code","1");
            rm.put("message","获取数据成功");
            rm.put("addresses",addresses);  //返回当前用户的所有地址
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","获取数据失败");
        }
        return rm;
    }

    @RequestMapping(value = "/reviseAddress",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Object> reviseAddress(@RequestBody Address address){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            addressService.update(address);
            rm.put("code","1");
            rm.put("message","修改数据成功");
            rm.put("addresses",addressService.queryAllByUserId(address.getUser().getId()));  //返回修改后
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","修改数据失败");
        }
        return rm;
    }



}
