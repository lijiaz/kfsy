package com.baizhi.entity;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 用户实体类
 * Created by MaXn on 2017/6/11.
 */
public class User implements Serializable{
    private String id;
    private String phone;
    private String nickName;
    private String password;
    private String salt;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private String status;

    private List<Address> addresses;   //一个用户有多个收货地址
    private List<Order> orders;   //一个用户有多个订单

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                ", addresses=" + addresses +
                ", orders=" + orders +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public User(String id, String phone, String nickName, String password, String salt, Date createDate, String status, List<Address> addresses, List<Order> orders) {
        this.id = id;
        this.phone = phone;
        this.nickName = nickName;
        this.password = password;
        this.salt = salt;
        this.createDate = createDate;
        this.status = status;
        this.addresses = addresses;
        this.orders = orders;
    }
}
