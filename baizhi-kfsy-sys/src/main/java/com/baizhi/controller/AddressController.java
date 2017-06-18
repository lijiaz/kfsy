package com.baizhi.controller;
import com.baizhi.entity.Address;
import com.baizhi.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;
/**
 * Created by MaXn on 2017/6/13.
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    @RequestMapping("/getAllByUserId")
    @ResponseBody
    public List<Address> getAllByUserId(String id){
        List<Address> addresses = addressService.queryAllByUserId(id);
        return addresses;
    }

}
