package com.example.service;

import com.example.entity.Lesson;
import com.example.mapper.AdminExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminExamService {
    @Autowired
    private AdminExamMapper adminExamMapper;

    public List<Lesson> findAllExamLesson() {
        System.out.println("========");
        return adminExamMapper.findAllExamLesson();
    }
}
