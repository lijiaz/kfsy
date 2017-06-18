package com.baizhi.test;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.AddressService;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by MaXn on 2017/6/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-basic.xml")
public class TestUserService {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        User user = new User();
        user.setPassword("123456");
        user.setNickName("买药");
        user.setPhone("13610523036");
        userService.add(user);
    }

    @Test
    public void testGetAll(){
        /*Page<User> users = userService.queryAll(1, 5);
        System.out.println(users);
        List<User> result = users.getResult();
        for (User user : result) {
            System.out.println(user);
        }*/
        /*List<Address> addresses = addressService.queryAllByUserId("70aac8f6-4958-45b9-a964-56694f5e50ac");
        System.out.println(addresses);*/

        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        calendar.add(Calendar.DATE,-10);
        Date i = calendar.getTime();
        /*List<User> users = userDao.selectCount(time, i);
        System.out.println(users);*/
        System.out.println(i);
        calendar.add(Calendar.DATE,7);
        System.out.println(calendar.getTime());

    }
}
