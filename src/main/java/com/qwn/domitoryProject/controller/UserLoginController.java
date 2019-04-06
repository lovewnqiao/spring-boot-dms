package com.qwn.domitoryProject.controller;

import com.qwn.domitoryProject.entity.User;
import com.qwn.domitoryProject.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/user"})
public class UserLoginController {

    /**
     * 最开始希望用Map的形式接参数，后来不用了，将请求对应的接受方式记录一下
     *
     * @RequestBody Map<String , Object> map      post请求
     * @RequestParam Map<String , Object> map     get请求
     */

    /**
     * 注入service
     */
    @Autowired
    private UserLoginService userLoginService;


    /**
     * 同时这个时候可以自己了解一下@Controller与@RestController的区别，以及@ResponseBody的用法。
     */

    /**
     * 跳转到用户登录页面
     *
     * @return 登录页面
     */
    @RequestMapping(value = {"/loginHtml"})
    public String loginHtml() {
        return "userLogin";
    }

    /**
     * 跳转到用户注册页面
     *
     * @return 注册页面
     */
    @RequestMapping(value = {"/registerpage"})
    public String registerpage() {
        return "register";
    }

    /**
     * 系统入口
     *
     * @return 入口页面
     */
    @RequestMapping(value = {"/helloWord"})
    public String helloWord() {
        return "helloWord";
    }

    /**
     * 登录成功 加载欢迎页面  返回String y页面的路径和名称
     * */
    @RequestMapping(value="welcome",method = {RequestMethod.POST,RequestMethod.GET})
    public  String welcome(){
        return "welcome";
    }



    /**
     * 获取用户名与密码，用户登录
     *
     * @return 登录成功页面
     */
    @ResponseBody
    @RequestMapping(value = {"/userLogin"})
    public ModelAndView userLogin(@RequestParam("userId") String userId,
                            @RequestParam("password") String password,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView();
//        if (StringUtils.isEmpty(userId)) {
//            model.setViewName("loginError");
//            return model;
//        }
//
//        if (StringUtils.isEmpty(password)) {
//            model.setViewName("loginError");
//            return model;
//        }

        User user = userLoginService.userLogin(userId,password);

        if (user != null) {                                                  //登录成功
            request.getSession().setAttribute("session_user", user);     //将用户信息放入session  用于后续的拦截器
            model.setViewName("index");
            //response.sendRedirect("../index");
        } else {
            model.addObject("MSG", "用户名或密码错误");
            model.setViewName("userLogin");
        }
        return model;
    }
    /**
     * 获取用户名与密码，管理员登录
     *
     * @return 登录成功页面
     */
    @ResponseBody
    @RequestMapping(value = {"/managerLogin"})
    public ModelAndView managerLogin(@RequestParam("userId") String userId,
                                  @RequestParam("password") String password,
                                  HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView();
//        if (StringUtils.isEmpty(userId)) {
//            model.setViewName("loginError");
//            return model;
//        }
//
//        if (StringUtils.isEmpty(password)) {
//            model.setViewName("loginError");
//            return model;
//        }

        User manager = userLoginService.managerLogin(userId,password);

        if (manager != null) {                                                  //登录成功
            request.getSession().setAttribute("session_user", manager);     //将用户信息放入session  用于后续的拦截器
            model.setViewName("managerIndex");
            //response.sendRedirect("../index");
        } else {
            model.addObject("MSG", "用户名或密码错误");
            model.setViewName("managerLogin");
        }
        return model;
    }

    /**
     * 注册新用户
     *
     * @return 注册结果
     */
    @ResponseBody
    @RequestMapping(value = {"/uregister"})
    public String addUser(@RequestParam("userId") String userId,
                          @RequestParam("password") String password,
                          @RequestParam("password2") String password2,
                          @RequestParam("name") String name,
                          @RequestParam("sex") String sex,
                          @RequestParam("age") Integer age,
                          @RequestParam("telephone") String telephone,
                          @RequestParam("building") String building,
                          @RequestParam("floor") String floor,
                          @RequestParam("room") Integer room) {

        if (StringUtils.isEmpty(userId)) {
            return "用户名不能为空";
        }

        if (StringUtils.isEmpty(password)) {
            return "密码不能为空";
        }

        if (StringUtils.isEmpty(password2)) {
            return "确认密码不能为空";
        }

        if (!password.equals(password2)) {
            return "两次密码不相同，注册失败！！";
        } else {
            int res = userLoginService.adduser(userId, password, name, sex, age, telephone, building, floor, room);
            if (res == 0) {
                return "注册失败！";
            } else {
                return "注册成功！";
            }
        }

    }

    /**
     * 用于测试拦截器（用户是否登录，查看session）
     * 查询用户列表  http://localhost:8080/user/queryAllUser
     *
     * @return 用户列表（json串）
     */
    @ResponseBody
    @RequestMapping(value = {"/queryAllUser"})
    public List<Map<String, Object>> queryAllUser() {

        return userLoginService.queryAllUser();
    }

}

