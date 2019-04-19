package com.qwn.domitoryProject.controller;


import com.qwn.domitoryProject.entity.Floor;
import com.qwn.domitoryProject.entity.Floor;
import com.qwn.domitoryProject.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/floor")
public class FloorController {
    static final int pageSize=10;
    @Autowired
    private FloorService floorService ;

    //楼层信息管理
    @RequestMapping(value = "/floorinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String userinfomation(HttpSession session) {
        return "/dormitory/floorinformation";
    }
    /**
     * 跳转到楼层新增页面
     *
     * @return 新增楼层页面
     */
    @RequestMapping(value = {"/floorAddPage"})
    public String floorAddpage() {
        return "/dormitory/floorAdd";
    }

    //删除楼层信息
    @ResponseBody
    @RequestMapping(value="delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteFloor(@PathVariable("key") Integer key){
        return floorService.delete(key);
    }

    //修改楼层信息
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int update(Floor floor){
        return floorService.update(floor);
    }

    /*
       查询楼层信息列表
    */
    @PostMapping(value = "/floorlist")
    @ResponseBody
    public Map floorlist(HttpServletRequest request){
        int page=Integer.parseInt(request.getParameter("page"));
        int pageSzie=Integer.parseInt(request.getParameter("rows"));//pageSzie
        int startRecord=(page-1)*pageSzie+1;
        int total=floorService.getFloorNumber();
        List<Floor> floorlist=floorService.selectAllFloor(startRecord,pageSzie);
        Map resultMap=new HashMap();
        resultMap.put("total",total-1);
        resultMap.put("rows",floorlist);
        return resultMap;
    }

    /**
     * 条件查询楼层信息列表
     */
    @PostMapping(value = "/conFloorList")
    @ResponseBody
    public Map conFloorList(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSzie = Integer.parseInt(request.getParameter("rows"));//pageSzie
        String buildingId = request.getParameter("buildingId");
        String floorId = request.getParameter("floorId");
        if("-".equals(buildingId)){
            buildingId=null;
        }
        int startRecord = (page - 1) * pageSzie + 1;
        int total = floorService.getFloorNumber();
        List<Floor> conFloorList = floorService.selectFloorByCondition(startRecord, pageSzie, buildingId,floorId);
        Map resultMap = new HashMap();
        resultMap.put("total", total - 1);
        resultMap.put("rows", conFloorList);
        return resultMap;
    }

    /**
     * 查询所有宿舍楼号
     *
     * @return
     */
    @RequestMapping("buildingId/names")
    @ResponseBody
    public Set getBuildingNames() {
        Set<String> set = new HashSet<>();
        set.add("-");
        set.add("1栋");
        set.add("2栋");
        set.add("3栋");
        set.add("4栋");
        Set<Map<String, String>> buildings = new HashSet<>();
        set.forEach(s -> {
            Map<String, String> map = new HashMap<>(1);
            map.put("text", s);
            buildings.add(map);
        });
        return buildings;
    }


    //新增楼层信息
    @ResponseBody
    @RequestMapping(value = "/floorAdd", produces = {"application/json;charset=UTF-8"})
    public String addFloor(@RequestParam("buildingId") String buildingId,
                          @RequestParam("floorId") String floorId,
                          @RequestParam("name") String name
                          ) {

        if (StringUtils.isEmpty(buildingId)) {
            return "宿舍楼不能为空";
        }

        if (StringUtils.isEmpty(floorId)) {
            return "楼层不能为空";
        }

        if (StringUtils.isEmpty(name)) {
            return "楼层类型为空，注册失败！！";
        } else {
            int res = floorService.addFloor(buildingId, floorId, name);
            if (res == 0) {
                return "注册失败！";
            } else {
                return "注册成功！";
            }
        }

    }

    /*
      修改楼层信息
   */
    @PostMapping(value="/update")
    @ResponseBody
    public Map<String,String> update(@RequestParam("id") Integer id,@RequestParam("buildingId") String buildingId,
                                     @RequestParam("floorId") String floorId, @RequestParam("name") String name,
                                    HttpSession session){

        Map<String,String> map=new HashMap<>();
        Floor floor=new Floor();
        floor.setId(id);
        floor.setBuildingId(buildingId);
        floor.setFloorId(floorId);
        floor.setName(name);
        try{
            floorService.update(floor);
            map.put("success","true");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","请核对信息确保登录名唯一");
        }
        return map;
    }

    /***
     * 删除楼层记录返回map形式
     *
     */
    @PostMapping(value = "/remove_floor")//删除楼层记录
    @ResponseBody
    public Map<String,String> removeFloors(@RequestParam("id") Integer id,HttpSession session){
        Map<String,String> result = new HashMap<>();
        if(StringUtils.isEmpty(String.valueOf(id))){
            result.put("msg","当前楼层id为空");
            return result;
        }
        try{
            floorService.delete(id);
            result.put("success","true");
            System.out.println("删除Id: "+id);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","Some errors occured.");
        }
        return result;
    }

}
