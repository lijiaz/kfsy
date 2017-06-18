package com.baizhi.entity;
import java.io.Serializable;
/**
 *
 * 订单项实体类
 * Created by MaXn on 2017/6/16.
 */
public class OrderItem implements Serializable {

    private String id;
    private String name;
    private Double price;
    private Integer num;
    private Double total;

    private Order order;  //一个订单项属于一个订单
    private Product product;  //一个商品属于一个订单项

    public OrderItem() {
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", total=" + total +
                ", order=" + order +
                ", product=" + product +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderItem(String id, String name, Double price, Integer num, Double total, Order order, Product product) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.num = num;
        this.total = total;
        this.order = order;
        this.product = product;
    }
}
