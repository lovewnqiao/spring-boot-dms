package com.qwn.domitoryProject.controller;

import com.qwn.domitoryProject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
    @Autowired
    HttpSession httpSession;
    @Autowired
    HttpServletRequest request;
    @Autowired
    NoticeService noticeService;

    /**
     * 通知管理页面
     *
     * @return
     */
    @RequestMapping("page/index")
    public String index() {
        return "notice/noticeIndex";
    }


    @RequestMapping("list/page")
    @ResponseBody
    public Map pageList(@RequestParam("page") Integer page,
                        @RequestParam("rows") Integer pageSize,
                        @RequestParam(required = false, value = "classifyId") Integer classifyId) {
        List list = noticeService.pageList(classifyId);
        int total = noticeService.noticeCount(classifyId);
        Map resultMap = new HashMap(2);
        resultMap.put("total", total);
        resultMap.put("rows", list);
        return resultMap;
    }

}
