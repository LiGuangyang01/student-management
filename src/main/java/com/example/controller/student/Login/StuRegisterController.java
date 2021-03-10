package com.example.controller.student.Login;

import com.example.entity.User;
import com.example.entity.StuInformation;
import com.example.service.StuUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "学生注册接口")
@Controller
@RequestMapping("/stu")
public class StuRegisterController {
    //属性注入
    @Autowired
    private StuUserService userService;
    /**
     * 跳转页面
     */
    @RequestMapping("/sturegister")
    public String register(){
        return "student/sturegister";
    }

   //接受Date类型数据
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    //判断注册的用户名是否存在
    @ApiOperation(value = "判断注册的用户名是否存在")
    @RequestMapping("/findReisterUsername")
    @ResponseBody
    public Map<String, Object> findRegisterUsername(String username){
        Map<String, Object> resultMap =  new HashMap<String,Object>();
        boolean isExist;
        if(userService.findReisterUsername(username)){
            isExist=true;
        }
        else{
            isExist=false;
        }
        resultMap.put("isExist", isExist);
       // return "sturegister";
        return resultMap;
    }
    /**
     * 注册逻辑处理
     */
    @ApiOperation(value = "学生用户注册")
    @RequestMapping("/StuRegister")
    public String toregister(String username, String password, Model model,
    String stuid,String stuname, String sex, Date stutime)
    {
        if(userService.findReisterUsername(username)){
            model.addAttribute("msg", "用户名已经存在");
            return "student/sturegister";
        }
        //进行Md5加密，第一个参数为密码，第二参数是以用户名为盐，第三个参数是加密的次数
        Md5Hash md5registepassword=new Md5Hash( password, username,5);
        System.out.println(md5registepassword.toString());
        User user=new User();
        user.setUsername(username);
        user.setRoles("stu");
        user.setPassword(md5registepassword.toString());
        //注册用户账号
        boolean addregister= userService.register(user);
        //注册用户详细信息
        StuInformation stuInformation=new StuInformation();
        stuInformation.setUsername(username);
        stuInformation.setSex(sex);
        stuInformation.setStuname(stuname);
        stuInformation.setStuid(stuid);
        stuInformation.setStutime(stutime);
        boolean addStuInformation= userService.addStuInformation(stuInformation);
        if(addregister&&addStuInformation) {
            model.addAttribute("msg", "注册成功");
        }
        else{
            model.addAttribute("msg", "注册失败");
        }
        return "student/sturegister";
    }

}
