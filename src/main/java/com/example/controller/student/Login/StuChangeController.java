package com.example.controller.student.Login;

import com.example.entity.User;
import com.example.entity.StuInformation;
import com.example.service.StuUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "学生信息接口")
@Controller
@RequestMapping("/stu")
public class StuChangeController {
    //属性注入
    @Autowired
    private StuUserService userService;

    //跳转到修改密码界面
    @RequestMapping("/toChangepassword")
    public String toChangepassword() {
        return "student/Changepassword";
    }

    @RequestMapping("/toChangeInformation")
    public String toChangeInformation() {
        return "student/changeInformation";
    }

    //修改密码，先加密认证原密码，再加密新密码进行修改
    @RequestMapping("/ChangePassword")
    public String ChangePassword(String password1, String password2, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Md5Hash md5password = new Md5Hash(password1, username, 5);
        String userpassword = md5password.toString();
//       System.out.println(userpassword);
        User user = userService.findByUsername(username);
        User user2 = new User();
        if (userpassword.equals(user.getPassword())) {
            Md5Hash md5password2 = new Md5Hash(password2, username, 5);
            user2.setUsername(username);
            user2.setPassword(md5password2.toString());
            userService.updatePassword(user2);
            model.addAttribute("msg", "修改成功");
        } else {
            model.addAttribute("msg", "原密码错误");
        }
        return "student/changepassword";
    }

    //返回学生信息，返回数据为json数据
       @ApiOperation(value = "学生信息")
          @RequestMapping("/ChangeInformation")
        @ResponseBody  // 用于转换对象为json
        public List<Map<String, Object>> list(HttpSession session) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> m = new HashMap<String, Object>();
        String username = (String) session.getAttribute("username");
        StuInformation stuInformation = userService.findInformationByUsername(username);
        //将数据库读取到的时间数据进行格式修改
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String stutime = simpleDateFormat.format(stuInformation.getStutime());
        m.put("stuid", stuInformation.getStuid());
        m.put("stuname", stuInformation.getStuname());
        m.put("sex", stuInformation.getSex());
        m.put("stutime", stutime);
        list.add(m);
        return list;
    }

//    //修改用户的信息
    @RequestMapping("/ChangeStuInformation")
    public String ChangeInformation(HttpSession session, Model model, String stuid, String stuname, String sex, String stutime) throws Exception {
        String username = (String) session.getAttribute("username");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stutime);
        //date.setDate(date.getDate() + 1);
        StuInformation stuInformation=new StuInformation();
        stuInformation.setUsername(username);
        stuInformation.setSex(sex);
        stuInformation.setStuname(stuname);
        stuInformation.setStuid(stuid);
        stuInformation.setStutime(date);
        boolean changeInformation= userService.updateInformation(stuInformation);
        if(changeInformation){
            model.addAttribute("msg", "修改成功");
        }
        else {
            model.addAttribute("msg", "修改失败");
        }
        return "student/changeInformation";
    }
    @RequestMapping("/counser")
    public String Counser(){
        return "student/counser";
    }

    @RequestMapping("/book")
    public String Book(){
        return "student/book";
    }

    @RequestMapping("/task")
    public String Task(){
        return "student/task";
    }
    @RequestMapping("/kebiao")
    public String Ke_biao(){
        return "student/example";
    }

    @RequestMapping("/leave")
    public String leave(){
        return "student/leave";
    }
}