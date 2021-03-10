package com.example.entity;

import java.sql.Timestamp;

public class Lesson {
    private Integer taotiid;
    private String lessonname;
    private Timestamp createtime;
   private Integer teacherid;

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    private String teachername;


    public String getLessonname() {
        return lessonname;
    }

    @Override
    public String toString() {
        return "ExamLesson{" +
                "taotiid=" + taotiid +
                ", lessonname='" + lessonname + '\'' +
                '}';
    }

    public Integer getTaotiid() {
        return taotiid;
    }

    public void setTaotiid(Integer taotiid) {
        this.taotiid = taotiid;
    }

    public void setLessonname(String lessonname) {
        this.lessonname = lessonname;
    }
}
