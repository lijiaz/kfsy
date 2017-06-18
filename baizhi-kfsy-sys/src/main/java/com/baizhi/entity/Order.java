package com.baizhi.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户订单 实体类
 * Created by MaXn on 2017/6/13.
 */
public class Order implements Serializable {

    private String id;
    private String orderNum;
    private Double totalPrice;
    private Date createDate;
    private String orderStatus;
    private String payStatus;
    private String remarks;   //订单备注

    private Address address;   //一个订单有一个收货地址
    private User user;   //一个订单属于一个用户
    private List<OrderItem> orderItems;  //一个订单中有多个订单项

    public Order() {
    }

    public Order(String id, String orderNum, Double totalPrice, Date createDate, String orderStatus, String payStatus, String remarks, Address address, User user, List<OrderItem> orderItems) {
        this.id = id;
        this.orderNum = orderNum;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.orderStatus = orderStatus;
        this.payStatus = payStatus;
        this.remarks = remarks;
        this.address = address;
        this.user = user;
        this.orderItems = orderItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", totalPrice=" + totalPrice +
                ", createDate=" + createDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", remarks='" + remarks + '\'' +
                ", address=" + address +
                ", user=" + user +
                ", orderItems=" + orderItems +
                '}';
    }
}
