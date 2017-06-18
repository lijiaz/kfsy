package com.baizhi.ws;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.*;
import com.baizhi.service.AddressService;
import com.baizhi.service.OrderService;
import com.baizhi.service.PillService;
import com.baizhi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by MaXn on 2017/6/16.
 */
@Controller
@RequestMapping("/cartServer")
public class CartWebService {

    @Resource
    private Jedis jedis;
    @Resource
    private PillService pillService;
    @Resource
    private OrderService orderService;
    @Resource
    private AddressService addressService;
    @Resource
    private UserService userService;


    //初始未登录状态下的添加购物车
    @RequestMapping(value = "/operateCartNoUser/{imei}/{productId}/{options}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCart(@PathVariable String productId, @PathVariable String imei,@PathVariable String options) {

        HashMap<String, Object> rm = new HashMap<String, Object>();
        try{
            if(options.equals("add")){   //判断是什么操作，add增加
                if (jedis.exists(imei)) {  //已经存在该客户端信息
                    if (jedis.hexists(imei, productId)) {   //如果该商品已经存在，修改数量
                        String hget = jedis.hget(imei, productId);
                        CartItem cartItem = JSONObject.parseObject(hget, CartItem.class);
                        cartItem.setNumber(cartItem.getNumber()+1);//修改数量
                        cartItem.setTotal(cartItem.getNumber()*cartItem.getPrice());
                        jedis.hset(imei,productId,JSONObject.toJSONString(cartItem));
                    } else{  //商品不存在,则存入
                        Product product = pillService.queryOneProduct(productId);
                        CartItem newCartItem = new CartItem(product.getName(), 1, product.getCurrentPrice(), product.getCurrentPrice());
                        jedis.hset(imei,productId, JSONObject.toJSONString(newCartItem));
                    }

                }else{  //该客户端第一次添加购物车
                    Product product = pillService.queryOneProduct(productId);
                    CartItem cartItem = new CartItem(product.getName(), 1, product.getCurrentPrice(), product.getCurrentPrice());
                    jedis.hset(imei,productId,JSONObject.toJSONString(cartItem));
                }
                rm.put("code","1");
                rm.put("message","修改成功");
                List<String> hvals = jedis.hvals(imei);
                List<CartItem> items = new ArrayList<CartItem>();
                for (String hval : hvals) {
                    CartItem cartItem = JSONObject.parseObject(hval, CartItem.class);
                    items.add(cartItem);
                }
                rm.put("itmes",items);   //将该用户的所有购物车项都返回
        }else{
            String hget = jedis.hget(imei, productId);
            CartItem cartItem = JSONObject.parseObject(hget, CartItem.class);
            if(cartItem.getNumber()>1){   //判断此时购物车中的数量是否大于1
                cartItem.setNumber(cartItem.getNumber()-1);//修改数量
                cartItem.setTotal(cartItem.getNumber()*cartItem.getPrice());
                jedis.hset(imei,productId,JSONObject.toJSONString(cartItem));
            }else{   //若购物车中的数量不大于1，则再进行减操作，直接删除
                jedis.hdel(imei,productId);
            }
        }
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","修改失败");
        }
        return rm;
    }


    //初始为登录状态的购物车操作服务



    //初始状态未登录，进行登录后的购物车操作服务
    @RequestMapping(value = "/toLogin/{imei}/{userId}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> toLogin(@PathVariable String imei,@PathVariable String userId){
        HashMap<String, Object> rm = new HashMap<String, Object>();
        Map<String, String> n = jedis.hgetAll(imei);   //未登录状态下的购物项
        try{
            if (jedis.exists(userId)){  //判断之前的该用户是否已经加入购物车
                Map<String, String> all = jedis.hgetAll(userId);
                for (String s : n.keySet()) {
                    all.put(s,n.get(s));
                }
                jedis.del(userId);  //删除该用户原有保存在redis中的购物项
                jedis.hmset(userId,all);    //将合并后的数据放入redis中
                List<String> hvals = jedis.hvals(userId);
                List<CartItem> items = new ArrayList<CartItem>();
                for (String hval : hvals) {
                    CartItem cartItem = JSONObject.parseObject(hval, CartItem.class);
                    items.add(cartItem);
                }
                rm.put("items",items);
            }else{
                jedis.hmset(userId,n);
            }
            rm.put("code","1");
            rm.put("message","同步数据成功");

        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","同步数据失败");
        }
        return  rm;
    }



    //清空购物车
    @RequestMapping(value = "/clearCart/{userId}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> clearCart(@PathVariable String userId){
        HashMap<String, Object> rm = new HashMap<String, Object>();
        try{
            jedis.del(userId);
            rm.put("code","1");
            rm.put("message","清空购物车成功");
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","清空购物车失败");
        }
        return rm;
    }

    //确认购买，生成未付款订单
    @RequestMapping(value = "/submit/{userId}/{addressId}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> submit(@PathVariable String userId,@PathVariable String addressId){
        HashMap<String, Object> rm = new HashMap<String, Object>();
        Map<String, String> sm = jedis.hgetAll(userId);
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        try{
            for (String s : sm.keySet()) {   //遍历所有购物项 key为商品id value为购物项对象
                Product product = pillService.queryOneProduct(s);
                CartItem cartItem = JSONObject.parseObject(sm.get(s), CartItem.class);  //购物项
                OrderItem item = new OrderItem();   //订单项
                item.setName(cartItem.getName());
                item.setNum(cartItem.getNumber());
                item.setPrice(cartItem.getPrice());
                item.setTotal(cartItem.getTotal());
                item.setProduct(product);
                orderItems.add(item);
            }
            Address address = addressService.queryOne(addressId);
            Order order = new Order();
            order.setAddress(address);
            order.setUser(userService.queryOne(userId));
            orderService.add(order,orderItems);
            rm.put("code","1");
            rm.put("message","操作成功");
        }catch (Exception e){
            e.printStackTrace();
            rm.put("code","-1");
            rm.put("message","操作失败");
        }
        return  rm;
    }



}
