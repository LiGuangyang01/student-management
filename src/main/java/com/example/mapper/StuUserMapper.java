package com.example.mapper;

import com.example.entity.User;
import com.example.entity.StuInformation;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository

public interface StuUserMapper {
    //登录
    public User findByUsername(String username);

    //判断用户名是否注册过
    public User findRegisterUsername(String username);
    //注册用户账号
    public int register(User user);
    //注册学生用户账号信息存储
    public int addStuInformation(StuInformation user);
   //修改用户密码
    public int updatePassword(User user);
    //查询用户信息
    public StuInformation findInformationByUsername(String username);
    //修改用户信息
    public int updateInformation(StuInformation user);
}