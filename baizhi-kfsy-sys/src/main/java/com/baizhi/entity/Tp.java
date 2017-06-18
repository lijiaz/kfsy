package com.baizhi.entity;
import java.io.Serializable;
/**
 * 商品和主题的联合 实体对象
 * Created by MaXn on 2017/6/14.
 */
public class Tp implements Serializable {

    private  String productid;
    private  String themeid;


    @Override
    public String toString() {
        return "Tp{" +
                "productid='" + productid + '\'' +
                ", themeid='" + themeid + '\'' +
                '}';
    }

    public Tp() {
    }

    public Tp(String productid, String themeid) {
        this.productid = productid;
        this.themeid = themeid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getThemeid() {
        return themeid;
    }

    public void setThemeid(String themeid) {
        this.themeid = themeid;
    }


}
