package com.example.service.impl;

import com.example.mapper.TeaResultExamMapper;
import com.example.entity.Lesson;
import com.example.entity.StuResult;
import com.example.service.TeaResultExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeaResultServiceImpl implements TeaResultExamService {
    //属性注入
    @Autowired
    private TeaResultExamMapper teaResultExamMapper;

    @Override
    public void addExamLesson(Lesson examLesson) {
        teaResultExamMapper.addExamLesson(examLesson);
    }

    @Override
    public List<Lesson> TeaExamlesson(String username) {
        return teaResultExamMapper.TeaExamlesson(username);
    }

    @Override
    public Integer findTeacheridByusername(String username) {
        return teaResultExamMapper.findTeacheridByusername(username);
    }

    @Override
    public List<StuResult> manageStuExam(Integer id) {
        return teaResultExamMapper.manageStuExam(id);
    }
}
