package com.qwn.domitoryProject.controller;


import com.qwn.domitoryProject.entity.Notice;
import com.qwn.domitoryProject.service.StuNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/student/notice")
public class StuNoticeController {
    @Autowired
    StuNoticeService stuNoticeService;

    @RequestMapping("noticeinfomation")
    public String index() {
        return "notice/stuIndex";
    }

    @RequestMapping("page/detail")
    public String detail(@RequestParam Integer id, Model model) {
        Notice notice = stuNoticeService.detail(id);
        model.addAttribute("notice", notice);
        return "notice/stuDetail";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<Map> list(@RequestParam(required = false, value = "classifyId") Integer classifyId,
                          @RequestParam(required = false, value = "title") String title) {
        return stuNoticeService.list(classifyId, title);
    }


}
