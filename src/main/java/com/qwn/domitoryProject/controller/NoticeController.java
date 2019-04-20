package com.qwn.domitoryProject.controller;


import com.qwn.domitoryProject.constant.DmsConstants;
import com.qwn.domitoryProject.entity.Notice;
import com.qwn.domitoryProject.entity.User;
import com.qwn.domitoryProject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpSession session;

    //通知分类信息管理
    @RequestMapping(value = "/noticeinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String noticeinfomation(HttpSession session) {
        return "/notice/noticeinformation";
    }

    /**
     * 跳转到新增通知分类页面
     *
     * @return 新增通知分类信息页面
     */
    @RequestMapping(value = {"/noticeAddPage"})
    public String noticeAddpage() {
        return "/notice/noticeAdd";
    }

    //删除通知分类信息
    @ResponseBody
    @RequestMapping(value = "delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteNotice(@PathVariable("key") Integer key) {
        return noticeService.delete(key);
    }


    /**
     * 修改通知分类信息
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public Map<String, String> update(Notice notice) {

        Map<String, String> map = new HashMap<>();
        try {
            noticeService.update(notice);
            map.put("success", "true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "请核对信息确保登录名唯一");
        }
        return map;
    }

    /*
       查询通知分类记录列表
    */
    @PostMapping(value = "/noticelist")
    @ResponseBody
    public Map noticelist(@RequestParam("page") Integer page,
                          @RequestParam("rows") Integer pageSzie,
                          @RequestParam(required = false, value = "classifyId") Integer classifyId,
                          @RequestParam(required = false, value = "title") String title) {

        int startRecord = (page - 1) * pageSzie + 1;
        int total = noticeService.getNoticenumber(classifyId, title);
        List noticelist = noticeService.selectAllNotice(classifyId, title);
        Map resultMap = new HashMap();
        resultMap.put("total", total);
        resultMap.put("rows", noticelist);
        return resultMap;
    }

    /*
      查询通知分类记录列表
   */
    @PostMapping(value = "/noticelistFindAll")
    @ResponseBody
    public List<Notice> noticelistFindAll() {
        List<Notice> noticelist = noticeService.noticelistFindAll();
        return noticelist;
    }

    //新增通知分类信息
    @ResponseBody
    @RequestMapping(value = "/noticeAdd", produces = {"application/json;charset=UTF-8"})
    public String addNotice(Notice notice, HttpSession session) {

        if (StringUtils.isEmpty(notice.getClassifyId())) {
            return "通知分类不能为空";
        }

        User user = (User) session.getAttribute(DmsConstants.SESSION_USER);
        if (user == null) {
            return "未登录，请先登录！";
        } else {
            notice.setApplicant(user.getName());

            int res = noticeService.addNotice(notice);
            if (res == 0) {
                return "新增通知分类失败！";
            } else {
                return "新增通知分类成功！";
            }
        }

    }


    /***
     * 删除通知分类信息返回map形式
     *
     */
    @PostMapping(value = "/remove_notice")//删除通知分类记录
    @ResponseBody
    public Map<String, String> removeNotices(@RequestParam("id") Integer id) {
        Map<String, String> result = new HashMap<>();
        if (StringUtils.isEmpty(String.valueOf(id))) {
            result.put("msg", "当前通知分类id为空");
            return result;
        }
        try {
            noticeService.delete(id);
            result.put("success", "true");
            System.out.println("删除Id: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", "Some errors occured.");
        }
        return result;
    }

}
