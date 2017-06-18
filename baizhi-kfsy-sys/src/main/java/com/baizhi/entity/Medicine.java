package com.baizhi.entity;
import java.io.Serializable;
import java.util.List;

/**
 * 药品实体类
 * Created by MaXn on 2017/6/14.
 */
public class Medicine implements Serializable{
    private String id;
    private String unit;    //单位
    private String feature;   //性状
    private String validDate;   //保质期
    private String ingredient;   //成分
    private String nmpn;   //国药准字号

    private List<Image> images;  //一个药品有多个图片

    public Medicine() {
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", unit='" + unit + '\'' +
                ", feature='" + feature + '\'' +
                ", validDate='" + validDate + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", nmpn='" + nmpn + '\'' +
                ", images=" + images +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getNmpn() {
        return nmpn;
    }

    public void setNmpn(String nmpn) {
        this.nmpn = nmpn;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Medicine(String id, String unit, String feature, String validDate, String ingredient, String nmpn, List<Image> images) {
        this.id = id;
        this.unit = unit;
        this.feature = feature;
        this.validDate = validDate;
        this.ingredient = ingredient;
        this.nmpn = nmpn;
        this.images = images;
    }
}
