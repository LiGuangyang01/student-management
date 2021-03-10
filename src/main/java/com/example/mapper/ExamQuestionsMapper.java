package com.example.mapper;

import com.example.entity.Questions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//@Mapper
@Repository
public interface ExamQuestionsMapper {
    //返回单选题目
    public List<Map<String,Object>> findExamRadioQuestions(Integer taotiid);
    //返回多选题目
    public List<Map<String,Object>> findExamCheckboxQuestions(Integer taotiid);
    public Questions findExamAnswerById(String id);

}
