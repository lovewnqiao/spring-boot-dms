package com.qwn.domitoryProject.service;

import com.qwn.domitoryProject.entity.User;
import com.qwn.domitoryProject.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserLoginService {

    /**
     * 注入dao
     */
    @Autowired
    private userMapper usermapper;

    //用户登录
    public User userLogin(String userId,String password){
        return usermapper.userlogin(userId,password);
    }

    //管理员登录
    public User managerLogin(String userId,String password){
        return usermapper.managerlogin(userId,password);
    }

    //注册新用户
    public int adduser(String userId,String password,String name,String sex,Integer age,String telephone,String building,String floor,Integer room){

        /**
         * 注意查看mapper中的注释
         */

        return usermapper.adduser(userId,password,name,sex,age,telephone,building,floor,room);
        //return usermapper.adduser1(userId,password,name);     //对应sql语句中的第二种注册方式
    }

    //查询用户列表
    public List<Map<String,Object>> queryAllUser(){
        return usermapper.queryAllUser();
    }
}
