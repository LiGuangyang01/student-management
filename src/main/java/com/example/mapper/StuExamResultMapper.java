package com.example.mapper;

import com.example.entity.StuInformation;
import com.example.entity.StuResult;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


//@Mapper
@Repository
public interface StuExamResultMapper {
    //添加学生考试成绩记录
    public int addStuExamResult(StuResult stuResult);

    public StuInformation findExamStuByUsername(String username);
    //返回用户考试所有记录
    public List<Map<String,Object>>findExamByUsername(String username);
}
