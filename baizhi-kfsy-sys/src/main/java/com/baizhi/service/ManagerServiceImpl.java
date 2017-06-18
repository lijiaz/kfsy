package com.baizhi.service;

import com.baizhi.dao.ManagerDao;
import com.baizhi.entity.Manager;
import com.baizhi.util.MD5Util;
import com.baizhi.util.SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
/**
 * 管理员 Service 层接口的实现类
 * Created by MaXn on 2017/6/12.
 */
@Service("managerService")
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    public void add(Manager manager) {
        manager.setId(UUID.randomUUID().toString());
        String salt = SaltUtil.getSalt(5);
        String newPassword = MD5Util.getMD5(manager.getPassword()+salt);
        manager.setSalt(salt);
        manager.setLev("1");
        manager.setPassword(newPassword);
        managerDao.insert(manager);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Manager queryOneByUserName(Manager manager) {
        Manager m = managerDao.selectOneByUserName(manager.getUserName());
        if(m==null){
            throw new RuntimeException("账号不存在,请检查账号");
        }else{
            if(MD5Util.getMD5(manager.getPassword()+m.getSalt()).equals(m.getPassword())){
                return m;
            }else{
                throw new RuntimeException("密码错误，请检查密码");
            }
        }
    }
}
