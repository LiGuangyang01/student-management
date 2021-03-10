package com.example.service;

import com.example.entity.User;
import com.example.entity.TeacherInformation;

import java.util.List;

public interface AdminUserService {
    //登录验证
    User findByUsername(String username);
    //删除loginuser
    void deleteLoginUser(Integer[] id);
   //返回所有管理员信息给前端页面
   List<User> findAdminByroles();
    //返回所有教师信息给前端页面
    List<TeacherInformation> findTeacherByroles();
    //增加或者修改各种用户
    void addLoginUsersave(User loginUser);
   //增加或者修改教师详细信息
   void  addteachersave(TeacherInformation teacherInformation);

    //根据id返回用户对象
    User findAdminById(Integer id);
    TeacherInformation findTeacherById(Integer id);



}
