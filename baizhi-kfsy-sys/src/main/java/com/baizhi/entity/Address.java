package com.baizhi.entity;

import java.io.Serializable;

/**
 *用户收货地址实体类
 * Created by MaXn on 2017/6/13.
 */
public class Address implements Serializable{

    private String id;
    private String receiver;   //收货人
    private String phone;
    private String address;

    private User user;   //一个收货地址对应一个用户

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", receiver='" + receiver + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address(String id, String receiver, String phone, String address, User user) {
        this.id = id;
        this.receiver = receiver;
        this.phone = phone;
        this.address = address;
        this.user = user;
    }
}
