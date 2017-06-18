package com.baizhi.entity;

import java.util.List;

/**
 * 用来二次封装easyui中表格数据格式
 * Created by MaXn on 2017/6/12.
 */
public class DataGrid<T>{

    private Integer total;
    private List<T> rows;

    public DataGrid() {
    }

    @Override
    public String toString() {
        return "DataGrid{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public DataGrid(Integer total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
