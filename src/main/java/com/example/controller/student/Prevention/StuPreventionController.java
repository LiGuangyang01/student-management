package com.example.controller.student.Prevention;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author：li
 * @Description：
 * @Data：Created in 上午 09:19 2021/3/10
 * @Modified：
 */
@Controller
@RequestMapping("/stu")
public class StuPreventionController {
   //体温打卡页面
   @RequestMapping("/card")
   public String toCard(){
       return "student/card";
   }

}
