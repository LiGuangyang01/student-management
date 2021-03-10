package com.example.mapper;

import com.example.entity.Lesson;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface ExamLessonMapper {
    /**
     *
     * 查询所有考试课程数据
     */

    public List<Lesson> findExamLesson();
}
