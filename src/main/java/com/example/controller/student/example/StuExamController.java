package com.example.controller.student.example;

import com.example.entity.Lesson;
import com.example.service.ExamLessonService;
import com.example.service.ExamQuestionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "学生考试信息")
@Controller
@RequestMapping("/stu")
public class StuExamController {
    @Autowired
    private ExamQuestionsService exampleQuestionsService;

    //属性注入
    @Autowired
    private ExamLessonService examLessonService;
    //属性注入

    //跳转到在线考试选择课程界面
    @RequestMapping("/toExam")
    public String toExam(){
        return "student/exam";
    }

    //返回考试科目选择，返回数据为json数据
    @ApiOperation(value = "考试科目选择")
    @RequestMapping("/ExamLessonlist")
    @ResponseBody  // 用于转换对象为json
    public List<Lesson> list(){
        List<Lesson> list =  examLessonService.findExamLesson();
        return list;
    }

    //返回考试题目
    @RequestMapping("/toExamPageList")
    public String toExamPage(Integer lessonid, Model model, HttpSession session) {
           //存储选择的考试课程id号
           session.setAttribute("taotiid",lessonid);
//        List<Map<String,Object>> questionsList  = exampleQuestionsService.findExamQuestions(3);
        //返回单选题目
        List<Map<String,Object>> questionsList  = exampleQuestionsService.findExamRadioQuestions(lessonid);
        model.addAttribute("radioQuestionsList", questionsList);
        //返回多选题目
        List<Map<String,Object>> questionsList2  = exampleQuestionsService.findExamCheckboxQuestions(lessonid);
        model.addAttribute("checkboxQuestionsList", questionsList2);
        return "student/exampage";
    }




    @RequestMapping("/toExamPage1")
    public String toExamPage1(Model model){
        Map<String, String> map = new HashMap<String, String>();
        map.put("name1", "value1");
        map.put("name2", "value2");
        map.put("name3", "value3");
        model.addAttribute("msg", map);
        return "welcome";
    }


}
