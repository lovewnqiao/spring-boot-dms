package com.qwn.domitoryProject.controller;


import com.qwn.domitoryProject.constant.DmsConstants;
import com.qwn.domitoryProject.entity.Repair;
import com.qwn.domitoryProject.service.RepairService;
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
@RequestMapping(value = "/repair")
public class RepairController {
    static final int pageSize=10;
    @Autowired
    private RepairService repairService ;

    //维修信息管理
    @RequestMapping(value = "/repairinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String userinfomation(HttpSession session) {
        return "/repair/repairinformation";
    }
    /**
     * 跳转到维修新增页面
     *
     * @return 新增维修页面
     */
    @RequestMapping(value = {"/repairAddPage"})
    public String repairAddpage() {
        return "/repair/repairAdd";
    }

    //删除维修记录
    @ResponseBody
    @RequestMapping(value="delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteRepair(@PathVariable("key") Integer key){
        return repairService.delete(key);
    }

    //修改维修记录
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int update(Repair repair){
        return repairService.update(repair);
    }

    /*
       查询维修记录列表
    */
    @PostMapping(value = "/repairlist")
    @ResponseBody
    public Map repairlist(HttpServletRequest request){
        int page=Integer.parseInt(request.getParameter("page"));
        int pageSzie=Integer.parseInt(request.getParameter("rows"));//pageSzie
        int startRecord=(page-1)*pageSzie+1;
        int total=repairService.getUsernumber();
        List<Repair> repairlist=repairService.selectAllRepair(startRecord,pageSzie);
        Map resultMap=new HashMap();
        resultMap.put("total",total-1);
        resultMap.put("rows",repairlist);
        return resultMap;
    }


    //新增维修记录
    @ResponseBody
    @RequestMapping(value = "/repairAdd", produces = {"application/json;charset=UTF-8"})
    public String addRepair(@RequestParam("buildingId") String buildingId,
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
            int res = repairService.addRepair(buildingId, floorId, roomId, name, descr, data, applicant);
            if (res == 0) {
                return "注册失败！";
            } else {
                return "注册成功！";
            }
        }

    }

    /*
      修改学生信息
   */
    @PostMapping(value="/update")
    @ResponseBody
    public Map<String,String> update(@RequestParam("id") Integer id,@RequestParam("buildingId") String buildingId, @RequestParam("floorId") String floorId,
                                     @RequestParam("roomId") String roomId, @RequestParam("name") String name,
                                     @RequestParam("descr") String descr, @RequestParam("data") String data,
                                     @RequestParam("applicant") String applicant,@RequestParam("state") Integer state,HttpSession session){

        Map<String,String> map=new HashMap<>();
        Repair repair=new Repair();
        repair.setId(id);
        repair.setBuildingId(buildingId);
        repair.setFloorId(floorId);
        repair.setName(name);
        repair.setRoomId(roomId);
        repair.setData(data);
        repair.setDescr(descr);
        repair.setApplicant(applicant);
        repair.setState(state);
        try{
            repairService.update(repair);
            map.put("success","true");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","请核对信息确保登录名唯一");
        }
        return map;
    }

    /***
     * 删除维修记录返回map形式
     *
     */
    @PostMapping(value = "/remove_repair")//删除维修记录
    @ResponseBody
    public Map<String,String> removeRepairs(@RequestParam("id") Integer id,HttpSession session){
        Map<String,String> result = new HashMap<>();
        if(StringUtils.isEmpty(String.valueOf(id))){
            result.put("msg","当前报修记录id为空");
            return result;
        }
        try{
            repairService.delete(id);
            result.put("success","true");
            System.out.println("删除Id: "+id);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","Some errors occured.");
        }
        return result;
    }

}
