package com.baizhi.service;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.util.MD5Util;
import com.baizhi.util.SaltUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by MaXn on 2017/6/12.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User queryOneByPhone(User user) {
        User u = userDao.selectOneByPhone(user.getPhone());
        if(u==null){
            throw new RuntimeException("账户不存在，请检查手机号");
        }else{
            if(MD5Util.getMD5(user.getPassword()+u.getSalt()).equals(u.getPassword())){
                return u;
            }else {
                throw new RuntimeException("密码错误,请检查密码");
            }
        }
    }

    public void add(User user) {
        user.setId(UUID.randomUUID().toString());
        String salt = SaltUtil.getSalt(5);
        String newPassword = MD5Util.getMD5(user.getPassword()+salt);
        user.setPassword(newPassword);
        user.setSalt(salt);
        user.setCreateDate(new Date());
        user.setStatus("1");
        userDao.insert(user);
    }

    public void remove(String id) {
        userDao.delete(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User queryOne(String id) {
        return userDao.selectOne(id);
    }

    //更新一个用户
    public void update(User user) {
        userDao.update(user);
    }

    //分页查询所有
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<User> queryAll(Integer pageNum, Integer pageSize) {
        Page<User> page = PageHelper.startPage(pageNum, pageSize);
        userDao.selectAll();
        return page;
    }

    //分页根据昵称模糊查询
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<User> queryByNickName(Integer pageNum, Integer pageSize, String nickName) {
        Page<User> page = PageHelper.startPage(pageNum, pageSize);
        userDao.selectByNickName(nickName);
        return page;
    }

    //分页根据手机号模糊查询
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<User> queryByPhone(Integer pageNum, Integer pageSize, String phone) {
        Page<User> page = PageHelper.startPage(pageNum, pageSize);
        userDao.selectByPhone(phone);
        return page;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> queryCount(Date currentDate, Date beforeDate) {
        return userDao.selectCount(currentDate,beforeDate);
    }


}
