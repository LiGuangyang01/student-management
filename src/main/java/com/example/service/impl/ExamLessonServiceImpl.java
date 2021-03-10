package com.example.service.impl;

import com.example.mapper.ExamLessonMapper;
import com.example.entity.Lesson;
import com.example.service.ExamLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamLessonServiceImpl implements ExamLessonService {
    //属性注入
    @Autowired
    private ExamLessonMapper examLessonMapper;
    @Override
    public List<Lesson> findExamLesson() {

        return examLessonMapper.findExamLesson();
    }
}
