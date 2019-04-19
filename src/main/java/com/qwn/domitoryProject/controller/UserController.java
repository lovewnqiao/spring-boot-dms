package com.qwn.domitoryProject.controller;


import com.qwn.domitoryProject.constant.DmsConstants;
import com.qwn.domitoryProject.entity.User;
import com.qwn.domitoryProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    //新增学生信息
    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user) {
        return userService.addUser(user);
    }

    //删除学生信息
    @ResponseBody
    @RequestMapping(value = "delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteUser(@PathVariable("key") String key) {
        return userService.delete(key);
    }

    //修改学生信息
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int update(User user) {
        return userService.update(user);
    }


    //学生信息管理
    @RequestMapping(value = "/userinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String userinfomation(HttpSession session) {
        return "/common/information";
    }

    //个人信息管理
    @RequestMapping(value = "/updateMyInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateMyInfo(HttpSession session) {
        return "/common/myInfo";
    }

    /*
       查询学生信息列表
    */
    @PostMapping(value = "/userinforlist")
    @ResponseBody
    public Map userinforlist(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSzie = Integer.parseInt(request.getParameter("rows"));//pageSzie
        String building = request.getParameter("building");
        if("-".equals(building)){
            building=null;
        }
        String name = request.getParameter("name");
        int startRecord = (page - 1) * pageSzie + 1;
        int total = userService.getUsernumber();
        List<User> userinforlist = userService.findAllUser(startRecord, pageSzie, building, name);
        Map resultMap = new HashMap();
        resultMap.put("total", total - 1);
        resultMap.put("rows", userinforlist);
        return resultMap;
    }

    /**
     * 查询所有宿舍楼号
     *
     * @return
     */
    @RequestMapping("building/names")
    @ResponseBody
    public List getBuildingNames() {
        List<String> set = new ArrayList<>();
        set.add("-");
        set.add("1栋");
        set.add("3栋");
        set.add("4栋");
        set.add("5栋");
        List<Map<String, String>> buildings = new ArrayList<>();
        set.forEach(s -> {
            Map<String, String> map = new HashMap<>(1);
            map.put("text", s);
            buildings.add(map);
        });


        return buildings;
    }


    /*
    进入学生信息界面
    */
    @GetMapping(value = "/info")
    public String stuinfor() {
        return "/common/information";
    }

    /*
              新增学生
      */
    @PostMapping(value = "/save_users")
    @ResponseBody
    public Map<String, String> saveUsers(@RequestParam("userId") String userId, @RequestParam("name") String name,
                                         @RequestParam("password") String password, @RequestParam("sex") String sex,
                                         @RequestParam("age") Integer age, @RequestParam("telephone") String telephone,
                                         @RequestParam("building") String building, @RequestParam("floor") String floor,
                                         @RequestParam("room") Integer room, HttpSession session) {

        Map<String, String> map = new HashMap<>();
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setName(name);
        user.setSex(sex);
        user.setAge(age);
        user.setTelephone(telephone);
        user.setBuilding(building);
        user.setFloor(floor);
        user.setRoom(room);
        try {
            userService.addUser(user);
            map.put("success", "true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "请核对信息确保登录名唯一");
        }
        return map;
    }

    /*
      修改学生信息
   */
    @PostMapping(value = "/update")
    @ResponseBody
    public Map<String, String> update(@RequestParam("userId") String userId, @RequestParam("name") String name,
                                      @RequestParam("password") String password, @RequestParam("sex") String sex,
                                      @RequestParam("age") Integer age, @RequestParam("telephone") String telephone,
                                      @RequestParam("building") String building, @RequestParam("floor") String floor,
                                      @RequestParam("room") Integer room, HttpSession session) {

        Map<String, String> map = new HashMap<>();
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setName(name);
        user.setSex(sex);
        user.setAge(age);
        user.setTelephone(telephone);
        user.setBuilding(building);
        user.setFloor(floor);
        user.setRoom(room);
        try {
            userService.update(user);
            map.put("success", "true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "请核对信息确保登录名唯一");
        }
        return map;
    }

    /***
     * 删除学生 返回map形式
     *
     */
    @PostMapping(value = "/remove_users")//删除学生
    @ResponseBody
    public Map<String, String> removeUsers(@RequestParam("id") String id, HttpSession session) {
        Map<String, String> result = new HashMap<>();
        if (((User) session.getAttribute(DmsConstants.SESSION_USER)).getUserId().equals(id)) {
            result.put("msg", "违法操作！不能删除自己！");
            return result;
        }
        try {
            userService.delete(id);
            result.put("success", "true");
            System.out.println("删除Id: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "Some errors occured.");
        }
        return result;
    }

    /***
     * 个人信息修改（年龄，性别，电话）
     */
    @RequestMapping(value = "/myInfo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView updateMyInfo(@RequestParam("userId") String userId, @RequestParam("name") String name, @RequestParam("sex") String sex,
                                     @RequestParam("age") Integer age, @RequestParam("telephone") String telephone,
                                     @RequestParam("building") String building, @RequestParam("floor") String floor,
                                     @RequestParam("room") Integer room, HttpSession session) {

        ModelAndView model = new ModelAndView();
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setSex(sex);
        user.setAge(age);
        user.setTelephone(telephone);
        user.setBuilding(building);
        user.setFloor(floor);
        user.setRoom(room);
        try {
            userService.update(user);
            model.addObject("messag", "修改成功");
            model.setViewName("/common/success");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("messag", "修改失败");
        model.setViewName("/common/error");
        return model;
    }


    /***
     * 个人信息修改
     */
    @RequestMapping(value = "myInfoPassword", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView myInfo(HttpSession session) {
        ModelAndView model = new ModelAndView();
        User user = (User) session.getAttribute(DmsConstants.SESSION_USER);
        String userId = user.getUserId();
        user.setUserId(userId);
        User logUser = userService.getUserById(user);
        session.setAttribute(DmsConstants.SESSION_USER, logUser);
        model.addObject(DmsConstants.SESSION_USER, logUser);
        model.setViewName("/common/myInfo");
        return model;
    }

    /***
     * 修改用户密码
     */
    @RequestMapping(value = "/modifypassword", method = {RequestMethod.POST, RequestMethod.GET})//保存新增用户和修改的用户信息
    @ResponseBody
    public ModelAndView modifypassword(@RequestParam("userId") String userId,
                                       @RequestParam("oldpassword") String oldpassword,
                                       @RequestParam("newpassword1") String newpassword1,
                                       @RequestParam("newpassword2") String newpassword2, HttpSession session) {
        ModelAndView model = new ModelAndView();
        User Loginuser = (User) session.getAttribute(DmsConstants.SESSION_USER);
        if (oldpassword == null || "".equals(oldpassword)) {
            model.addObject("messag", "初始密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (newpassword1 == null || "".equals(newpassword1)) {
            model.addObject("messag", "新密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (newpassword2 == null || "".equals(newpassword2)) {
            model.addObject("messag", "确认密码不能为空");
            model.setViewName("/common/success");
            return model;
        } else if (!newpassword2.equals(newpassword1)) {
            model.addObject("messag", "两次密码不一致");
            model.setViewName("/common/success");
            return model;
        } else if (!Loginuser.getPassword().equals(oldpassword)) {
            model.addObject("messag", "初始密码不正确");
            model.setViewName("/common/success");
            return model;
        }

        User user = new User();
        user.setUserId(userId);
        user.setPassword(newpassword2);
        try {
            userService.update(user);
            model.addObject("messag", "修改成功");
            model.setViewName("/common/success");
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("messag", "修改失败");
        model.setViewName("/common/success");
        return model;
    }

    /***
     * 退出系统，返回到首页
     */
    @RequestMapping(value = "/exit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView exit(HttpSession session) {
        session.removeAttribute(DmsConstants.SESSION_USER);
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/user/helloWord");
        return model;
    }

}
