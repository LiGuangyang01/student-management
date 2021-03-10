package com.example.controller.admin;

import com.example.entity.Lesson;
import com.example.service.AdminExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminExamController {

    @Autowired
    private AdminExamService adminExamService;
    //管理员查看所有考试课程页面
    @RequestMapping("/tomanageExam")
    public String Manageadmin(){
        return "admin/manageExam";
    }
    //传输所有管理员用户信息
    @RequestMapping("/findAllExamLesson")
    @ResponseBody  // 用于转换对象为json
    public  List<Lesson> findAllExamLesson(){
        return adminExamService.findAllExamLesson();
    }
}
