package com.baizhi.entity;

import java.io.Serializable;

/**
 * 轮播图 实体类
 * Created by MaXn on 2017/6/13.
 */
public class LbImage implements Serializable{

    private String id;
    private String name;
    private String url;
    private String status;

    public LbImage() {
    }

    public LbImage(String id, String name, String url, String status) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.status = status;
    }

    @Override
    public String toString() {
        return "LbImage{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
