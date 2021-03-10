package com.example.entity;

import java.util.Date;

public class StuInformation {
    private String stuid;
    private String stuname;
    private String roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private  Integer id;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    @Override
    public String toString() {
        return "StuInformation{" +
                "stuid='" + stuid + '\'' +
                ", stuname='" + stuname + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", stutime=" + stutime +
                '}';
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Date getStutime() {
        return stutime;
    }

    public void setStutime(Date stutime) {
        this.stutime = stutime;
    }

    private String username;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String sex;
    private Date stutime;
}
