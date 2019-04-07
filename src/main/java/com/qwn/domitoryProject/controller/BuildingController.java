package com.qwn.domitoryProject.controller;


import com.qwn.domitoryProject.entity.Building;
import com.qwn.domitoryProject.service.BuildingService;
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
@RequestMapping(value = "/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService ;

    //宿舍楼信息管理
    @RequestMapping(value = "/buildinginfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String buildinginfomation(HttpSession session) {
        return "/dormitory/buildinginformation";
    }
    /**
     * 跳转到新增宿舍楼页面
     *
     * @return 新增宿舍楼信息页面
     */
    @RequestMapping(value = {"/buildingAddPage"})
    public String buildingAddpage() {
        return "/dormitory/buildingAdd";
    }

    //删除宿舍楼信息
    @ResponseBody
    @RequestMapping(value="delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteBuilding(@PathVariable("key") Integer key){
        return buildingService.delete(key);
    }

    //修改宿舍楼信息
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int update(Building building){
        return buildingService.update(building);
    }

    /*
       查询宿舍楼记录列表
    */
    @PostMapping(value = "/buildinglist")
    @ResponseBody
    public Map buildinglist(HttpServletRequest request){
        int page=Integer.parseInt(request.getParameter("page"));
        int pageSzie=Integer.parseInt(request.getParameter("rows"));//pageSzie
        int startRecord=(page-1)*pageSzie+1;
        int total=buildingService.getUsernumber();
        List<Building> buildinglist=buildingService.selectAllBuilding(startRecord,pageSzie);
        Map resultMap=new HashMap();
        resultMap.put("total",total-1);
        resultMap.put("rows",buildinglist);
        return resultMap;
    }


    //新增宿舍楼信息
    @ResponseBody
    @RequestMapping(value = "/buildingAdd", produces = {"application/json;charset=UTF-8"})
    public String addBuilding(@RequestParam("buildingId") String buildingId,
                            @RequestParam("name") String name) {

        if (StringUtils.isEmpty(buildingId)) {
            return "宿舍楼不能为空";
        }

        if (StringUtils.isEmpty(name)) {
            return "维修类型为空，注册失败！！";
        } else {
            int res = buildingService.addBuilding(buildingId,name);
            if (res == 0) {
                return "注册失败！";
            } else {
                return "注册成功！";
            }
        }

    }

    /**
     * 修改宿舍楼信息
     */
    @PostMapping(value="/update")
    @ResponseBody
    public Map<String,String> update(@RequestParam("id") Integer id,@RequestParam("buildingId") String buildingId,
                                     @RequestParam("name") String name,HttpSession session){

        Map<String,String> map=new HashMap<>();
        Building building=new Building();
        building.setId(id);
        building.setBuildingId(buildingId);
        building.setName(name);
        try{
            buildingService.update(building);
            map.put("success","true");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","请核对信息确保登录名唯一");
        }
        return map;
    }

    /***
     * 删除宿舍楼信息返回map形式
     *
     */
    @PostMapping(value = "/remove_building")//删除宿舍楼记录
    @ResponseBody
    public Map<String,String> removeBuildings(@RequestParam("id") Integer id,HttpSession session){
        Map<String,String> result = new HashMap<>();
        if(StringUtils.isEmpty(String.valueOf(id))){
            result.put("msg","当前宿舍楼id为空");
            return result;
        }
        try{
            buildingService.delete(id);
            result.put("success","true");
            System.out.println("删除Id: "+id);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","Some errors occured.");
        }
        return result;
    }

}
