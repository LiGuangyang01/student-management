package com.example.service;

import com.example.entity.Lesson;
import com.example.entity.Questions;

import java.util.List;

public interface TeacherIndexService {
    public List<Questions>ExamQuestionByteacherid(Integer id);
    public List<Lesson> findExamLessonByteacherid(Integer id);
    public Questions findExamQuestionsById(Integer id);
    //保存增加或者修改的考试试题
    public void saveExamQuestions(Questions questions);

}
