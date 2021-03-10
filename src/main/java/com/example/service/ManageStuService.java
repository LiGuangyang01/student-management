package com.example.service;

import com.example.entity.User;
import com.example.entity.StuInformation;

import java.util.List;

public interface ManageStuService {
    //根据id返回学生用户对象
    public User findStudentById(Integer id);
    //返回所有学生用户信息给前端页面
    public List<StuInformation> findStudentByroles();
    //修改学生用户数据
    public void updateStuUser(User loginUser);
}
