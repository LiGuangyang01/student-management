package com.example.entity;
import java.sql.Timestamp;

public class StuResult {
    private Integer id;
    private Integer taotiid;

    @Override
    public String toString() {
        return "StuResult{" +
                "id=" + id +
                ", taotiid=" + taotiid +
                ", username='" + username + '\'' +
                ", lessonname='" + lessonname + '\'' +
                ", radioscores=" + radioscores +
                ", checkscores=" + checkscores +
                ", total=" + total +
                ", createtime=" + createtime +
                ", stuid='" + stuid + '\'' +
                ", stuname='" + stuname + '\'' +
                '}';
    }

    private String username;
    private String lessonname;

    public String getLessonname() {
        return lessonname;
    }

    public void setLessonname(String lessonname) {
        this.lessonname = lessonname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaotiid() {
        return taotiid;
    }

    public void setTaotiid(Integer taotiid) {
        this.taotiid = taotiid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRadioscores() {
        return radioscores;
    }

    public void setRadioscores(Integer radioscores) {
        this.radioscores = radioscores;
    }

    public Integer getCheckscores() {
        return checkscores;
    }

    public void setCheckscores(Integer checkscores) {
        this.checkscores = checkscores;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    private Integer radioscores;

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

    private Integer checkscores;
    private Integer total;
    private Timestamp createtime;
    private  String stuid;
    private  String stuname;

}
