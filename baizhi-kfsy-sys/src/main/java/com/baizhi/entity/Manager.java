package com.baizhi.entity;
import java.io.Serializable;
/**
 * 管理员实体类
 * Created by MaXn on 2017/6/12.
 */
public class Manager implements Serializable{

    private String id;
    private String userName;
    private String password;
    private String salt;
    private String lev;

    public Manager() {
    }

    public Manager(String id, String userName, String password, String salt, String lev) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.lev = lev;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", lev='" + lev + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLev() {
        return lev;
    }

    public void setLev(String lev) {
        this.lev = lev;
    }
}
