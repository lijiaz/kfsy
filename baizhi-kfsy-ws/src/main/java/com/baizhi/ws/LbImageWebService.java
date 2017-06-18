package com.baizhi.ws;

import com.baizhi.entity.LbImage;
import com.baizhi.service.LbImageService;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/lbImageServer")
public class LbImageWebService {

    @Resource
    private LbImageService lbImageService;

    //查询所有在前台可以展示的轮播图
    @RequestMapping(value = "/getAllLb",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAllLb(){
        Map<String, Object> rm = new HashMap<String, Object>();
        try{
            List<LbImage> lbImages = lbImageService.queryAllUse();
            rm.put("code","1");
            rm.put("message","查询数据成功");
            rm.put("images",lbImages);  //返回所有轮播图
        }catch (Exception e){
            rm.put("code","-1");
            rm.put("message","查询数据失败");
        }
        return rm;
    }


}
