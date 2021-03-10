package com.example.service.impl;

import com.example.mapper.StuUserMapper;
import com.example.entity.User;
import com.example.entity.StuInformation;
import com.example.service.StuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StuUserServiceImpl implements StuUserService {
    //属性注入
    @Autowired
    private StuUserMapper usermapper;


    //登录验证
    @Override
    public User findByUsername(String username) {
        return usermapper.findByUsername(username);
    }

    //注册判断用户名是否存在
    @Override
    public boolean findReisterUsername(String username) {
        if (usermapper.findRegisterUsername(username) == null) {
            return false;
        } else
            return true;
    }


    //判断注册是否增加成功
    @Override
    public boolean register(User user) {
        int appregister = 0;
        appregister = usermapper.register(user);
        // System.out.println("appregister:"+appregister);
        if (appregister == 0) {
            return false;
        } else {
            return true;
        }
    }


    //查询用户信息
    @Override
    public StuInformation findInformationByUsername(String username) {
        return usermapper.findInformationByUsername(username);
    }

    //增加用户信息
    @Override
    public boolean addStuInformation(StuInformation user) {
        int appregister = 0;
        appregister = usermapper.addStuInformation(user);
        if (appregister == 0) {
            return false;
        } else {
            return true;
        }
    }

    //修改用户密码
    @Override
    public boolean updatePassword(User user) {
        if (usermapper.updatePassword(user) == 0) {
            return false;
        } else
            return true;
    }

    //修改用户信息
    @Override
    public boolean updateInformation(StuInformation user) {
        if (usermapper.updateInformation(user) == 0) {
            return false;
        } else
            return true;
    }

}