package com.example.controller.student.Login;

import com.example.entity.User;
import com.example.service.StuUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "学生用户登录接口")
@Controller
@RequestMapping("/stu")
public class StuLoginController {

    //属性注入
    @Autowired
    private StuUserService userService;

    @RequestMapping("/index")
    public String toindex(){
        return "student/index";
    }

    /**
     * 登录逻辑处理
     */
    @ApiOperation(value = "学生用户登录")
    @RequestMapping(value = "/loginsubmit")
    public String login(String username, String password, Model model, HttpSession session) {
        /**
         * 使用Shiro编写认证操作
         */
        //判断用户名是否存在
        if(!userService.findReisterUsername(username)){
            model.addAttribute("msg", "用户名不存在");
            return "student/login";
        }
        //判断用户是否是学生用户
        User user = userService.findByUsername(username);
        if (!user.getRoles().equals("stu")) {
            model.addAttribute("msg", "登录失败");
            return "student/login";
        }
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //进行Md5加密，第一个参数为密码，第二参数是以用户名为盐，第三个参数是加密的次数
        Md5Hash md5password = new Md5Hash(password, username, 5);
        System.out.println(md5password.toString());
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, md5password.toString());
        //3.执行登录方法
        try {
            subject.login(token);

            session.setAttribute("username", username);
            //登录成功
            //跳转到对应的学生界面
            return "redirect:/stu/index";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "student/login";
        } catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            model.addAttribute("msg", "密码错误");
            return "student/login";
        }
    }


}
