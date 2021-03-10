package com.example.service.impl;

import com.example.mapper.ManageStuMapper;
import com.example.entity.User;
import com.example.entity.StuInformation;
import com.example.service.ManageStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageStuServiceImpl implements ManageStuService {
    //属性注入
    @Autowired
    private ManageStuMapper manageStuMapper;

    @Override
    public void updateStuUser(User loginUser) {
        manageStuMapper.updateStuUser(loginUser);

    }

    @Override
    public User findStudentById(Integer id) {
        return manageStuMapper.findStudentById(id);
    }


    @Override
      public List<StuInformation> findStudentByroles(){
        return manageStuMapper.findStudentByroles("stu");
    }
}
