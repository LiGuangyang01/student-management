package com.example.service;

import com.example.entity.Lesson;
import com.example.entity.StuResult;

import java.util.List;

public interface TeaResultExamService {
    //返回所有自己考试课程下学生的考试成绩
    public List<StuResult> manageStuExam(Integer id);
    public Integer findTeacheridByusername(String username);
    public List<Lesson> TeaExamlesson(String username);
    //增加考试课程
    public void addExamLesson(Lesson examLesson);
}
