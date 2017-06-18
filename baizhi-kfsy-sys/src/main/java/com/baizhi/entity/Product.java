package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 药品相关实体类
 * Created by MaXn on 2017/6/13.
 */
public class Product implements Serializable{

    private String id;
    private String name;
    private String treatment;   //药效
    private Double currentPrice;  //当前价格
    private Double oldPrice;   //原价
    private Integer stock;  //库存
    private Integer sales;  //销量

    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;   //入库时间
    private String status;   //状态
    private String thumbnail;   //缩略图

    private Category category;    //一个药品属于一个类型
    private List<Theme> themes;   //一种药品可以属于多个主题

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", treatment='" + treatment + '\'' +
                ", currentPrice=" + currentPrice +
                ", oldPrice=" + oldPrice +
                ", stock=" + stock +
                ", sales=" + sales +
                ", entryDate=" + entryDate +
                ", status='" + status + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", category=" + category +
                ", themes=" + themes +
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

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public Product() {
    }

    public Product(String id, String name, String treatment, Double currentPrice, Double oldPrice, Integer stock, Integer sales, Date entryDate, String status, String thumbnail, Category category, List<Theme> themes) {
        this.id = id;
        this.name = name;
        this.treatment = treatment;
        this.currentPrice = currentPrice;
        this.oldPrice = oldPrice;
        this.stock = stock;
        this.sales = sales;
        this.entryDate = entryDate;
        this.status = status;
        this.thumbnail = thumbnail;
        this.category = category;
        this.themes = themes;
    }
}
