package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 药品主题实体类
 * Created by MaXn on 2017/6/13.
 */
public class Theme implements Serializable{

    private String id;
    private String name;
    private String url;  //主题图片保存路径
    private String description;

    private List<Product> products;  //一个主题下有多种药品

    public Theme() {
    }

    public Theme(String id, String name, String url, String description, List<Product> products) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.description = description;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
