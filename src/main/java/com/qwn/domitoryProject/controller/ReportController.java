package com.qwn.domitoryProject.controller;


import com.qwn.domitoryProject.entity.Report;
import com.qwn.domitoryProject.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/report")
public class ReportController {
    @Autowired
    private ReportService reportService ;

    //学生入住信息管理
    @RequestMapping(value = "/reportinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String reportinfomation(HttpSession session) {
        return "/report/reportinformation";
    }
    /**
     * 跳转到新增学生入住页面
     *
     * @return 新增学生入住信息页面
     */
    @RequestMapping(value = {"/reportAddPage"})
    public String reportAddpage() {
        return "/report/reportAdd";
    }

    //删除学生入住信息
    @ResponseBody
    @RequestMapping(value="delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteReport(@PathVariable("key") Integer key){
        return reportService.delete(key);
    }

    //修改学生入住信息
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int update(Report report){
        return reportService.update(report);
    }

    /*
       查询学生入住记录列表
    */
    @PostMapping(value = "/reportlist")
    @ResponseBody
    public Map reportlist(HttpServletRequest request){
        int page=Integer.parseInt(request.getParameter("page"));
        int pageSzie=Integer.parseInt(request.getParameter("rows"));//pageSzie
        int startRecord=(page-1)*pageSzie+1;
        int total=reportService.getReportNumber();
        List<Report> reportlist=reportService.selectAllReport(startRecord,pageSzie);
        Map resultMap=new HashMap();
        resultMap.put("total",total-1);
        resultMap.put("rows",reportlist);
        return resultMap;
    }

    /**
     * 条件查询学生入住信息
     */
    @PostMapping(value = "/conReportList")
    @ResponseBody
    public Map conRepairList(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSzie = Integer.parseInt(request.getParameter("rows"));//pageSzie
        String dormitory = request.getParameter("dormitory");
        String studentName = request.getParameter("studentName");
        String classes = request.getParameter("classes");
        if("-".equals(dormitory)){
            dormitory=null;
        }
        String data = request.getParameter("data");
        int startRecord = (page - 1) * pageSzie + 1;
        int total = reportService.getReportNumber();
        List<Report> conRepairList = reportService.selectReportByCondition(startRecord, pageSzie, studentName, classes,dormitory,data);
        Map resultMap = new HashMap();
        resultMap.put("total", total - 1);
        resultMap.put("rows", conRepairList);
        return resultMap;
    }

    /**
     * 查询所有宿舍楼号
     *
     * @return
     */
    @RequestMapping("dormitory/names")
    @ResponseBody
    public Set getDormitoryNames() {
        Set<String> set = new HashSet<>();
        set.add("-");
        set.add("1108");
        set.add("1208");
        set.add("1308");
        set.add("1408");
        Set<Map<String, String>> dormitorys = new HashSet<>();
        set.forEach(s -> {
            Map<String, String> map = new HashMap<>(1);
            map.put("text", s);
            dormitorys.add(map);
        });
        return dormitorys;
    }


    //新增学生入住信息
    @ResponseBody
    @RequestMapping(value = "/reportAdd", produces = {"application/json;charset=UTF-8"})
    public String addReport(@RequestParam("studentId") String studentId,@RequestParam("studentName") String studentName,
                            @RequestParam("classes") String classes,@RequestParam("telephone") String telephone,
                            @RequestParam("dormitory") String dormitory, @RequestParam("data") String data) {

        if (StringUtils.isEmpty(studentId)) {
            return "学生学号不能为空";
        }
        if (StringUtils.isEmpty(studentName)) {
            return "学生姓名为空，新增失败！！";
        } else {
            int res = reportService.addReport(studentId,studentName,classes,telephone,dormitory,data);
            if (res == 0) {
                return "新增失败！";
            } else {
                return "新增成功！";
            }
        }

    }

    /**
     * 修改学生入住信息
     */
    @PostMapping(value="/update")
    @ResponseBody
    public Map<String,String> update(@RequestParam("id") Integer id,@RequestParam("studentId") String studentId,@RequestParam("studentName") String studentName,
                                     @RequestParam("classes") String classes,@RequestParam("telephone") String telephone,
                                     @RequestParam("dormitory") String dormitory, @RequestParam("data") String data,HttpSession session){

        Map<String,String> map=new HashMap<>();
        Report report=new Report();
        report.setId(id);
        report.setStudentId(studentId);
        report.setStudentName(studentName);
        report.setClasses(classes);
        report.setTelephone(telephone);
        report.setDormitory(dormitory);
        report.setData(data);
        try{
            reportService.update(report);
            map.put("success","true");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","请核对信息确保登录名唯一");
        }
        return map;
    }

    /***
     * 删除学生入住信息返回map形式
     *
     */
    @PostMapping(value = "/remove_report")//删除学生入住记录
    @ResponseBody
    public Map<String,String> removeReports(@RequestParam("id") Integer id,HttpSession session){
        Map<String,String> result = new HashMap<>();
        if(StringUtils.isEmpty(String.valueOf(id))){
            result.put("msg","当前入住信息id为空");
            return result;
        }
        try{
            reportService.delete(id);
            result.put("success","true");
            System.out.println("删除Id: "+id);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","Some errors occured.");
        }
        return result;
    }

}
