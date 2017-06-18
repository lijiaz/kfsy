package com.baizhi.test;

import com.baizhi.dao.ManagerDao;
import com.baizhi.entity.Manager;
import com.baizhi.service.ManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by MaXn on 2017/6/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-basic.xml")
public class TestManagerService {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ManagerDao managerDao;

    @Test
    public void test(){
        managerService.add(new Manager(null,"wyx","123456",null,null));
        //managerDao.insert(new Manager(UUID.randomUUID().toString(),"zs","123456","s3wda","1"));
    }
}
