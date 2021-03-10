package com.example.controller.admin;

import com.example.entity.User;
import com.example.entity.StuInformation;
import com.example.service.ManageStuService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManageStuController {


    //属性注入
    @Autowired
    private ManageStuService manageStuService;
    //设计Map聚合存储需要给页面的对象数据
    private Map<String, Object> result = new HashMap<String, Object>();

    //管理员管理学生用户页面
    @RequestMapping("/tomanagestudent")
    public String tomanageadteacher() {
        return "admin/managestudent";
    }


    //传输所有学生用户信息
    @RequestMapping("/managestudent")
    @ResponseBody  // 用于转换对象为json
    public List<StuInformation> studentlist(){
        List<StuInformation> list= manageStuService.findStudentByroles();
        return list;
    }

    /**
     * 根据id 查询对象
     */
    @RequestMapping("/studentfindById")
    @ResponseBody
    public User studentfindById(Integer id){
        User loginUser = manageStuService.findStudentById(id);
        return loginUser;
    }
    //    /**
//     * 保存数据,修改学生用户密码
//     */
    @RequestMapping("/savestudent")
    @ResponseBody
    public Map<String,Object> addAdmin(User loginUser){
        try {
            Md5Hash md5=new Md5Hash(loginUser.getPassword(),loginUser.getUsername(),5);
            System.out.println(md5.toString());
            loginUser.setPassword(md5.toString());
            manageStuService.updateStuUser(loginUser);
            result.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }


}