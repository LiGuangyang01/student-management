package com.example.mapper;

import com.example.entity.User;
import com.example.entity.StuInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface ManageStuMapper {
    //根据id返回学生用户对象
    public User findStudentById(Integer id);
    //返回所有学生用户信息给前端页面
    public List<StuInformation> findStudentByroles(String roles);
    //修改学生用户数据
    public void updateStuUser(User loginUser);

}
