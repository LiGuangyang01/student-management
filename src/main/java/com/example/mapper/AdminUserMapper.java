package com.example.mapper;

import com.example.entity.User;
import com.example.entity.TeacherInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface AdminUserMapper {
    //登录
    public User findByUsername(String username);

    //返回所有管理员用户信息给前端页面
    public List<User> findAdminByroles(String roles);

    //返回所有教师用户信息给前端页面
    public List<TeacherInformation> findTeacherByroles(String roles);
    //增加各种登录用户
    public void addLoginUser(User loginUser);

     //修改各种用户数据
    public void updateLoginUser(User loginUser);

    //根据id查找教师详细表的teacherId
     public int findTeacheridById(Integer id);

    //增加教师用户详细信息
    public void addTeacherInformation(TeacherInformation teacherInformation);
    //修改教师用户数据
    public void updateTeacherInformation(TeacherInformation teacherInformation);

    //根据id返回管理员用户对象
    public User findAdminById(Integer id);
    //
    public TeacherInformation findTeacherById(Integer id);
    public void deleteLoginUser(Integer[] id);
}
