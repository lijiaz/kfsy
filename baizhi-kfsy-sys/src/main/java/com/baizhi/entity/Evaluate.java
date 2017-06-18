package com.baizhi.entity;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户评价实体类
 * Created by MaXn on 2017/6/13.
 */
public class Evaluate implements Serializable{

    private String id;
    private String content;
    private String star;
    @JSONField(format = "yyyy-MM-dd")
    private Date createDate;

    private Order order;  //一个评价属于一个订单
    private User user;  //一个评价属于一个用户

    public Evaluate() {
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", star='" + star + '\'' +
                ", createDate=" + createDate +
                ", order=" + order +
                ", user=" + user +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Evaluate(String id, String content, String star, Date createDate, Order order, User user) {
        this.id = id;
        this.content = content;
        this.star = star;
        this.createDate = createDate;
        this.order = order;
        this.user = user;
    }
}
