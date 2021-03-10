package com.example.mapper;

import com.example.entity.Lesson;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminExamMapper {

    public List<Lesson> findAllExamLesson();
}
