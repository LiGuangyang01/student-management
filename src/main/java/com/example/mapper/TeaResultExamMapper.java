package com.example.mapper;

import com.example.entity.Lesson;
import com.example.entity.StuResult;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface TeaResultExamMapper {
    //返回所有自己考试课程下学生的考试成绩
    public List<StuResult> manageStuExam(Integer id);
    public Integer findTeacheridByusername(String username);
    //返回教师自己出的考试课程信息
    public List<Lesson> TeaExamlesson(String username);
    //增加考试课程
    public void addExamLesson(Lesson examLesson);

}
