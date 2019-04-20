package com.qwn.domitoryProject.service;

import com.qwn.domitoryProject.entity.User;

import java.util.List;

/**
 *
 *  study boot by kyh 2018/5/29
 */
public interface UserService {
    /****
     * 添加用户
     */

     int addUser(User user);
    /**
     * 分页查询
     */
     List<User> findAllUser(int pageNum, int pageSize);

     List<User> findAllUser(int pageNum, int pageSize, String building, String name);

    User getById(Integer id);

    /**
      * 删除用户
      * */
     int  delete(String key);

     /***
      * 修改用户
      * */
     int  update(User user);
    /**
     * 用户登录查询
     * */
    public  User getUserById(User user);

    public int getUsernumber();

}

