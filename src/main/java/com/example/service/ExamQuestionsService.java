package com.example.service;

import com.example.entity.Questions;

import java.util.List;
import java.util.Map;

public interface ExamQuestionsService {
    public List<Map<String,Object>> findExamRadioQuestions(Integer taotiid);
    public List<Map<String,Object>> findExamCheckboxQuestions(Integer taotiid);
    public Questions findExamAnswerById(String id);
}
