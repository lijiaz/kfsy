package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 药品种类的实体类
 * Created by MaXn on 2017/6/12.
 */
public class Category implements Serializable {

    private String id;
    private String name;
    private String url;

    private List<Product> products;  //一个类别下有多个商品

    public Category() {
    }

    public Category(String id, String name, String url, List<Product> products) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", products=" + products +
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
