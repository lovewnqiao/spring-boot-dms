package com.qwn.domitoryProject.controller;


import com.qwn.domitoryProject.entity.Classify;
import com.qwn.domitoryProject.service.ClassifyService;
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
@RequestMapping(value = "/classify")
public class ClassifyController {
    @Autowired
    private ClassifyService classifyService ;

    //通知分类信息管理
    @RequestMapping(value = "/classifyinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String classifyinfomation(HttpSession session) {
        return "/notice/classifyinformation";
    }
    /**
     * 跳转到新增通知分类页面
     *
     * @return 新增通知分类信息页面
     */
    @RequestMapping(value = {"/classifyAddPage"})
    public String classifyAddpage() {
        return "/notice/classifyAdd";
    }

    //删除通知分类信息
    @ResponseBody
    @RequestMapping(value="delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteClassify(@PathVariable("key") Integer key){
        return classifyService.delete(key);
    }

    //修改通知分类信息
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int update(Classify classify){
        return classifyService.update(classify);
    }

    /*
       查询通知分类记录列表
    */
    @PostMapping(value = "/classifylist")
    @ResponseBody
    public Map classifylist(HttpServletRequest request){
        int page=Integer.parseInt(request.getParameter("page"));
        int pageSzie=Integer.parseInt(request.getParameter("rows"));//pageSzie
        int startRecord=(page-1)*pageSzie+1;
        int total=classifyService.getClassifynumber();
        List<Classify> classifylist=classifyService.selectAllClassify(startRecord,pageSzie);
        Map resultMap=new HashMap();
        resultMap.put("total",total-1);
        resultMap.put("rows",classifylist);
        return resultMap;
    }


    //新增通知分类信息
    @ResponseBody
    @RequestMapping(value = "/classifyAdd", produces = {"application/json;charset=UTF-8"})
    public String addClassify(@RequestParam("classifyName") String classifyName,
                            @RequestParam("descr") String descr) {

        if (StringUtils.isEmpty(classifyName)) {
            return "通知分类不能为空";
        }

        if (StringUtils.isEmpty(descr)) {
            return "通知分类描述为空，注册失败！！";
        } else {
            int res = classifyService.addClassify(classifyName,descr);
            if (res == 0) {
                return "新增通知分类失败！";
            } else {
                return "新增通知分类成功！";
            }
        }

    }

    /**
     * 修改通知分类信息
     */
    @PostMapping(value="/update")
    @ResponseBody
    public Map<String,String> update(@RequestParam("id") Integer id,@RequestParam("classifyName") String classifyName,
                                     @RequestParam("descr") String descr,HttpSession session){

        Map<String,String> map=new HashMap<>();
        Classify classify=new Classify();
        classify.setId(id);
        classify.setClassifyName(classifyName);
        classify.setDescr(descr);
        try{
            classifyService.update(classify);
            map.put("success","true");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","请核对信息确保登录名唯一");
        }
        return map;
    }

    /***
     * 删除通知分类信息返回map形式
     *
     */
    @PostMapping(value = "/remove_classify")//删除通知分类记录
    @ResponseBody
    public Map<String,String> removeClassifys(@RequestParam("id") Integer id,HttpSession session){
        Map<String,String> result = new HashMap<>();
        if(StringUtils.isEmpty(String.valueOf(id))){
            result.put("msg","当前通知分类id为空");
            return result;
        }
        try{
            classifyService.delete(id);
            result.put("success","true");
            System.out.println("删除Id: "+id);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","Some errors occured.");
        }
        return result;
    }

}
