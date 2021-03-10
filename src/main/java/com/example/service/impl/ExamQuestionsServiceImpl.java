package com.example.service.impl;

import com.example.mapper.ExamQuestionsMapper;
import com.example.entity.Questions;
import com.example.service.ExamQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamQuestionsServiceImpl implements ExamQuestionsService {
    //属性注入
    @Autowired
    private ExamQuestionsMapper examQuestionsMapper;

    @Override
    public List<Map<String,Object>> findExamRadioQuestions(Integer taotiid) {

        return examQuestionsMapper.findExamRadioQuestions(taotiid);
    }

    @Override
    public List<Map<String, Object>> findExamCheckboxQuestions(Integer taotiid) {
        return examQuestionsMapper.findExamCheckboxQuestions(taotiid);
    }


    @Override
    public Questions findExamAnswerById(String id) {

        return examQuestionsMapper.findExamAnswerById(id);
    }
}