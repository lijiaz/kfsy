package com.baizhi.entity;

import java.io.Serializable;

/**
 * Created by MaXn on 2017/6/16.
 */
public class CartItem implements Serializable{

    private String name;
    private Integer number;
    private Double total;
    private Double price;

    public CartItem() {
    }

    public CartItem(String name, Integer number, Double total, Double price) {
        this.name = name;
        this.number = number;
        this.total = total;
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", total=" + total +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
