package com.example.controller.teacher;

import com.example.entity.Lesson;
import com.example.entity.StuResult;
import com.example.service.TeaResultExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TeaResultExamController {
    @Autowired
    private TeaResultExamService teaResultExamService;

    //设计Map聚合存储需要给页面的对象数据
    private Map<String,Object> result = new HashMap<String,Object>();
//传输所有自己课程考试学生的成绩信息
    @RequestMapping("/manageStuExam")
    @ResponseBody  // 用于转换对象为json
    public List<StuResult> manageStuExam(HttpSession session){
        String username= (String) session.getAttribute("TeaUsername");
         Integer teacherid=teaResultExamService.findTeacheridByusername(username);
        List<StuResult> list=teaResultExamService.manageStuExam(teacherid);
        return list;
    }


    @RequestMapping("/toExamlesson")
    public String tologin(){
        return "teacher/Examlesson";
    }
    //查询自己的所有考试课程信息
@RequestMapping("/teaExamLesson")
@ResponseBody  // 用于转换对象为json
public List<Lesson> TeaExamlesson(HttpSession session)
{
    String username= (String) session.getAttribute("TeaUsername");
    List <Lesson> list=teaResultExamService.TeaExamlesson(username);
    return list;
}

//增加考试课程
    @RequestMapping("/saveExamlesson")
    @ResponseBody
    public Map<String,Object> saveExamLesson(Lesson examLesson, HttpSession session){
        try {
            String username= (String) session.getAttribute("TeaUsername");
            Integer teacherid=teaResultExamService.findTeacheridByusername(username);
            examLesson.setTeacherid(teacherid);
            examLesson.setCreatetime(new Timestamp(new Date().getTime()));
            teaResultExamService.addExamLesson(examLesson);
            result.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
