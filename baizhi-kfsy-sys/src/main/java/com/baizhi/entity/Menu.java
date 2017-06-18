package com.baizhi.entity;
import java.io.Serializable;
import java.util.List;
/**
 * 后台管理系统菜单实体类
 * Created by MaXn on 2017/6/12.
 */
public class Menu implements Serializable {

    private String id;
    private String title;
    private String icon;
    private String href;
    private String lev;

    //关系属性
    private List<Menu> children;

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", lev='" + lev + '\'' +
                ", children=" + children +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getLev() {
        return lev;
    }

    public void setLev(String lev) {
        this.lev = lev;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Menu(String id, String title, String icon, String href, String lev, List<Menu> children) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.lev = lev;
        this.children = children;
    }
}
