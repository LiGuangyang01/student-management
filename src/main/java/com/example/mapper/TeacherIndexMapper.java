package com.example.mapper;

import com.example.entity.Lesson;
import com.example.entity.Questions;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface TeacherIndexMapper {

    public List<Questions> ExamQuestionByteacherid(Integer id);
    public  List<Lesson> findExamLessonByteacherid(Integer id);
    public Questions findExamQuestionsById(Integer id);

    //增加试题
    public void addExamQuestions(Questions questions);

    //修改试题
    public void updateExamQuestions(Questions questions);

}
