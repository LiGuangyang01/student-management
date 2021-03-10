package com.example.controller;

import com.example.shiro.ShiroConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private ShiroConfig shiroConfig;

   //教师和管理员登录页
    @RequestMapping("/alogin")
    public String admin_to_login(){
        return "admin/adminLogin";
    }
   //教师页面
    @RequestMapping("/teaindex")
    public String teacher_index(){
        return "teacher/teaindex";
    }
   //管理员页面
    @RequestMapping("/adminindex")
    public String admin_index(){
        return "admin/adminindex";
    }
    //学生认证
    @RequestMapping("/stu/tologin")
    public String stu_to_login(){
        return "student/login";
    }
    @RequestMapping("/")
    public String stu_to_login1(){
        return "student/login";
    }
    //Shiro认证
    @RequestMapping("/adminloginsubmit")
    public String login(String username, String password, Model model, HttpSession session) {
        return shiroConfig.authentication(username, password, model, session);}


}

