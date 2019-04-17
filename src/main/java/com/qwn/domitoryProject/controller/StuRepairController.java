package com.qwn.domitoryProject.controller;


import com.qwn.domitoryProject.constant.DmsConstants;
import com.qwn.domitoryProject.entity.StuRepair;
import com.qwn.domitoryProject.entity.User;
import com.qwn.domitoryProject.service.StuRepairService;
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
@RequestMapping(value = "/stuRepair")
public class StuRepairController {
    static final int pageSize=10;
    @Autowired
    private StuRepairService stuRepairService ;

    //学生维修信息管理
    @RequestMapping(value = "/stuRepairinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String userinfomation(HttpSession session) {
        return "/repair/stuRepairinformation";
    }
    /**
     * 跳转到学生维修新增页面
     *
     * @return 学生新增维修页面
     */
    @RequestMapping(value = {"/stuRepairAddPage"})
    public String stuRepairAddpage() {
        return "/repair/stuRepairAdd";
    }

    //学生删除维修记录
    @ResponseBody
    @RequestMapping(value="delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteStuRepair(@PathVariable("key") Integer key){
        return stuRepairService.delete(key);
    }

    //学生修改维修记录
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int update(StuRepair stuRepair){
        return stuRepairService.update(stuRepair);
    }

    /*
       学生查询维修记录列表
    */
    @PostMapping(value = "/stuRepairlist")
    @ResponseBody
    public Map stuRepairlist(HttpServletRequest request,HttpSession session){
        //获取登录用户得信息
        User Loginuser = (User) session.getAttribute(DmsConstants.SESSION_USER);
        String applicant=Loginuser.getName();
        int page=Integer.parseInt(request.getParameter("page"));
        int pageSzie=Integer.parseInt(request.getParameter("rows"));//pageSzie
        //String applicant=request.getParameter("applicant");
        int startRecord=(page-1)*pageSzie+1;
        int total=stuRepairService.getStuRepairnumber();
        List<StuRepair> stuRepairlist=stuRepairService.selectStuRepairByName(startRecord,pageSzie,applicant);
        Map resultMap=new HashMap();
        resultMap.put("total",total-1);
        resultMap.put("rows",stuRepairlist);
        return resultMap;
    }


    //学生新增维修记录
    @ResponseBody
    @RequestMapping(value = "/stuRepairAdd", produces = {"application/json;charset=UTF-8"})
    public String addStuRepair(@RequestParam("buildingId") String buildingId,
                          @RequestParam("floorId") String floorId,
                          @RequestParam("roomId") String roomId,
                          @RequestParam("name") String name,
                          @RequestParam("descr") String descr,
                          @RequestParam("data") String data,
                          @RequestParam("applicant") String applicant
                          ) {

        if (StringUtils.isEmpty(buildingId)) {
            return "宿舍楼不能为空";
        }

        if (StringUtils.isEmpty(floorId)) {
            return "楼层不能为空";
        }

        if (StringUtils.isEmpty(roomId)) {
            return "房间不能为空";
        }

        if (StringUtils.isEmpty(name)) {
            return "维修类型为空，注册失败！！";
        } else {
            int res = stuRepairService.addStuRepair(buildingId, floorId, roomId, name, descr, data, applicant);
            if (res == 0) {
                return "注册失败！";
            } else {
                return "注册成功！";
            }
        }

    }

    /*
      学生修改维修信息
   */
    @PostMapping(value="/update")
    @ResponseBody
    public Map<String,String> update(@RequestParam("id") Integer id,@RequestParam("buildingId") String buildingId, @RequestParam("floorId") String floorId,
                                     @RequestParam("roomId") String roomId, @RequestParam("name") String name,
                                     @RequestParam("descr") String descr, @RequestParam("data") String data,
                                     @RequestParam("applicant") String applicant,@RequestParam("state") Integer state,HttpSession session){

        Map<String,String> map=new HashMap<>();
        StuRepair stuRepair=new StuRepair();
        stuRepair.setId(id);
        stuRepair.setBuildingId(buildingId);
        stuRepair.setFloorId(floorId);
        stuRepair.setName(name);
        stuRepair.setRoomId(roomId);
        stuRepair.setData(data);
        stuRepair.setDescr(descr);
        stuRepair.setApplicant(applicant);
        stuRepair.setState(state);
        try{
            stuRepairService.update(stuRepair);
            map.put("success","true");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","请核对信息确保登录名唯一");
        }
        return map;
    }

    /***
     * 学生删除维修记录返回map形式
     *
     */
    @PostMapping(value = "/remove_stuRepair")//学生删除维修记录
    @ResponseBody
    public Map<String,String> removeStuRepairs(@RequestParam("id") Integer id,HttpSession session){
        Map<String,String> result = new HashMap<>();
        if(StringUtils.isEmpty(String.valueOf(id))){
            result.put("msg","当前报修记录id为空");
            return result;
        }
        try{
            stuRepairService.delete(id);
            result.put("success","true");
            System.out.println("删除Id: "+id);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","Some errors occured.");
        }
        return result;
    }

}
