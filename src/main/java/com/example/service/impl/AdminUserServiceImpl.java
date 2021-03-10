package com.example.service.impl;

import com.example.mapper.AdminUserMapper;
import com.example.entity.User;
import com.example.entity.TeacherInformation;
import com.example.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminUserServiceImpl implements AdminUserService {
    //属性注入
    @Autowired
    private AdminUserMapper adminUserMapper;


    //返回所有管理员用户的信息
    @Override
    public List<User> findAdminByroles() {

        return adminUserMapper.findAdminByroles("admin");
    }

    @Override
    public User findAdminById(Integer id) {
        return adminUserMapper.findAdminById(id);
    }

    @Override
    public void deleteLoginUser(Integer[] id) {
        adminUserMapper.deleteLoginUser(id);
    }

    @Override
    public List<TeacherInformation> findTeacherByroles() {
        return adminUserMapper.findTeacherByroles("teacher");
    }

    @Override
    public void addteachersave(TeacherInformation teacherInformation) {
        if(teacherInformation.getId()==null){
            adminUserMapper.addTeacherInformation(teacherInformation);
        }
        else{
            Integer teacherid=adminUserMapper.findTeacheridById(teacherInformation.getId());
            teacherInformation.setTeacherid(teacherid);
            adminUserMapper.updateTeacherInformation(teacherInformation);
        }
    }

    @Override
    public TeacherInformation findTeacherById(Integer id) {
        return adminUserMapper.findTeacherById(id);
    }

    @Override
    public void addLoginUsersave(User loginUser) {
        if(loginUser.getId()==null){
            adminUserMapper.addLoginUser(loginUser);
        }
        else{
            adminUserMapper.updateLoginUser(loginUser);
        }
    }

    //登录验证
    @Override
    public User findByUsername(String username) {
        return  adminUserMapper.findByUsername(username);
    }
}
