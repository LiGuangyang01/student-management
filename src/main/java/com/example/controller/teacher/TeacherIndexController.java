package com.example.controller.teacher;


import com.example.entity.Lesson;
import com.example.entity.Questions;
import com.example.service.TeaResultExamService;
import com.example.service.TeacherIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TeacherIndexController {

    //设计Map聚合存储需要给页面的对象数据
    private Map<String,Object> result = new HashMap<String,Object>();

    //属性注入
    @Autowired
    private TeaResultExamService teaResultExamService;
    //属性注入
    @Autowired
    private TeacherIndexService teacherIndexService;
    //学生考试成绩查询页面
    @RequestMapping("/teaResultShow")
    public String ResultShow(){
        return "teacher/ResultShow";
    }


    //考试题目显示操作页面
    @RequestMapping("/toExamQuestion")
    public String toExamQuestion(){
        return "teacher/ExamQuestion";
    }


    //返回所有考试试题
    @RequestMapping("/ExamQuestion")
    @ResponseBody  // 用于转换对象为json
    public List<Questions>  ExamQuestionByteacherid(HttpSession session)
    {
        String username= (String) session.getAttribute("TeaUsername");
        Integer teacherid=teaResultExamService.findTeacheridByusername(username);
        List <Questions> list=teacherIndexService.ExamQuestionByteacherid(teacherid);
        return list;
    }

    //返回考试科目选择，返回数据为json数据
    @RequestMapping("/TeacherExamLessonlist")
    @ResponseBody  // 用于转换对象为json
    public List<Lesson> list(HttpSession session){
        String username= (String) session.getAttribute("TeaUsername");
        Integer teacherid=teaResultExamService.findTeacheridByusername(username);
        List<Lesson> list = teacherIndexService.findExamLessonByteacherid(teacherid);
        return list;
    }

    @RequestMapping("/saveExamQuestions")
    @ResponseBody
    public Map<String,Object> saveExamQuestions(Questions questions){
        try {
//            System.out.println("id:"+questions.getId());
//            System.out.println("taotiid:"+questions.getTaotiid());
//            System.out.println("answer:"+questions.getAnswer());
          //  String taotiid=questions.getTaotiid();
            teacherIndexService.saveExamQuestions(questions);
            result.put("success", true);

        }catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;

    }
    /**
     * 根据id 查询对象
     */
    @RequestMapping("/findExamQuestionsById")
    @ResponseBody
    public Questions findExamQuestionsById(Integer id){

        Questions questions=teacherIndexService.findExamQuestionsById(id);
        return questions;
    }

}
