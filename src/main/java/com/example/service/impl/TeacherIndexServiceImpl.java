package com.example.service.impl;

import com.example.mapper.TeacherIndexMapper;
import com.example.entity.Lesson;
import com.example.entity.Questions;
import com.example.service.TeacherIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherIndexServiceImpl implements TeacherIndexService {
    //属性注入
    @Autowired
    private TeacherIndexMapper teacherIndexMapper;

    @Override
    public void saveExamQuestions(Questions questions) {
      if(questions.getId()==null){
          teacherIndexMapper.addExamQuestions(questions);
      }
      else
      {
          teacherIndexMapper.updateExamQuestions(questions);
      }
    }

    @Override
    public Questions findExamQuestionsById(Integer id) {
        return teacherIndexMapper.findExamQuestionsById(id);
    }

    @Override
    public List<Lesson> findExamLessonByteacherid(Integer id) {
        return teacherIndexMapper.findExamLessonByteacherid(id);
    }

    @Override
    public List<Questions> ExamQuestionByteacherid(Integer id) {
        return teacherIndexMapper.ExamQuestionByteacherid(id);
    }
}
