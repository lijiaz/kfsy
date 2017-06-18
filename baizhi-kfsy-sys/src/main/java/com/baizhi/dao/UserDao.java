package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
/**
 * 用户相关的dao层接口
 * Created by MaXn on 2017/6/12.
 */
public interface UserDao extends BaseDao<User> {

    //根据手机号查询一个
    User selectOneByPhone(String phone);

    //根据姓名模糊查询
    List<User> selectByNickName(String nickName);

    //根据手机号模糊查询
    List<User> selectByPhone(String phone);

    //查询在某段时间注册人数
    List<User> selectCount(@Param("currentDate") Date currentDate, @Param("beforeDate") Date beforeDate);
}
