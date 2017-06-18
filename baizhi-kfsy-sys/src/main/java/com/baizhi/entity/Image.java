package com.baizhi.entity;
import java.io.Serializable;
/**
 * 与药品有关的图片实体类
 * Created by MaXn on 2017/6/14.
 */
public class Image implements Serializable{

    private String id;
    private String name;
    private String url;
    private String type;    //图片类型 1代表轮播图 2代表详情图

    private Medicine medicine;    //一个图片属于一个药品

    public Image() {
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", medicine=" + medicine +
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Image(String id, String name, String url, String type, Medicine medicine) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.medicine = medicine;
    }
}
