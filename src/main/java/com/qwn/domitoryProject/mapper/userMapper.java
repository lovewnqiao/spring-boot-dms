package com.qwn.domitoryProject.mapper;

import com.qwn.domitoryProject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface userMapper {

    //用户登录
    User userlogin(@Param("userId") String userId,@Param("password") String password);
    //管理员登录
    User managerlogin(@Param("userId") String userId,@Param("password") String password);

    /**
     * 在这里写了两种新建用户的方式(具体查看sql语句)：
     *     1、id自增：即id每次加1这种
     *     2、利用UUID()生成id，在存入用户
     * 若采用第1种方式，需要对数据库中的id字段做一些修改，将id的类型改为int型，同时定义为可以自增。
     * 若采用第2种方式，则将将id的类型改为varchar型，同时取消自增。
     *
     * 无论再用那种方式，都需要注意实体类中的类型是否与数据库一致，若不一致，项目运行时报错。
     */


    //注册新用户(方式1)
    int adduser(@Param("userId") String userId, @Param("password") String password, @Param("name") String name,
                @Param("sex") String sex, @Param("age") Integer age, @Param("telephone") String telephone,
                @Param("building") String building, @Param("floor") String floor, @Param("room") Integer room);

    //注册新用户（方式2）
    int adduser1(@Param("userId") String userId, @Param("password") String password, @Param("name") String name);

    //查询用户列表
    List<Map<String,Object>> queryAllUser();
    //other
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    // 查询全部用户
    List<User> selectAllUser();
    /***
     * 用户登录
     */
    User selectselectByKeyAndPassword(User user);
    int getUsernumber();

}