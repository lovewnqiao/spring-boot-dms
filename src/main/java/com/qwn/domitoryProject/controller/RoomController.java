package com.qwn.domitoryProject.controller;


import com.qwn.domitoryProject.entity.Repair;
import com.qwn.domitoryProject.entity.Room;
import com.qwn.domitoryProject.service.RepairService;
import com.qwn.domitoryProject.service.RoomService;
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
@RequestMapping(value = "/room")
public class RoomController {
    static final int pageSize=10;
    @Autowired
    private RoomService roomService ;

    //房间信息管理
    @RequestMapping(value = "/roominfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String userinfomation(HttpSession session) {
        return "/dormitory/roominformation";
    }
    /**
     * 跳转到新增房间页面
     *
     * @return 新增房间信息页面
     */
    @RequestMapping(value = {"/roomAddPage"})
    public String roomAddpage() {
        return "/dormitory/roomAdd";
    }

    //删除房间信息
    @ResponseBody
    @RequestMapping(value="delete/{key}", produces = {"application/json;charset=UTF-8"})
    public int deleteRoom(@PathVariable("key") Integer key){
        return roomService.delete(key);
    }

    //修改房间信息
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int update(Room room){
        return roomService.update(room);
    }

    /*
       查询房间记录列表
    */
    @PostMapping(value = "/roomlist")
    @ResponseBody
    public Map roomlist(HttpServletRequest request){
        int page=Integer.parseInt(request.getParameter("page"));
        int pageSzie=Integer.parseInt(request.getParameter("rows"));//pageSzie
        int startRecord=(page-1)*pageSzie+1;
        int total=roomService.getUsernumber();
        List<Room> roomlist=roomService.selectAllRoom(startRecord,pageSzie);
        Map resultMap=new HashMap();
        resultMap.put("total",total-1);
        resultMap.put("rows",roomlist);
        return resultMap;
    }


    //新增房间信息
    @ResponseBody
    @RequestMapping(value = "/roomAdd", produces = {"application/json;charset=UTF-8"})
    public String addRoom(@RequestParam("buildingId") String buildingId,
                          @RequestParam("floorId") String floorId,
                          @RequestParam("roomId") String roomId,
                          @RequestParam("studentName") String studentName
                          ) {

        if (StringUtils.isEmpty(buildingId)) {
            return "房间不能为空";
        }

        if (StringUtils.isEmpty(floorId)) {
            return "楼层不能为空";
        }

        if (StringUtils.isEmpty(roomId)) {
            return "房间不能为空";
        }

        if (StringUtils.isEmpty(studentName)) {
            return "房间类型为空，注册失败！！";
        } else {
            int res = roomService.addRoom(buildingId, floorId, roomId, studentName);
            if (res == 0) {
                return "注册失败！";
            } else {
                return "注册成功！";
            }
        }

    }

    /**
     * 修改房间信息
   */
    @PostMapping(value="/update")
    @ResponseBody
    public Map<String,String> update(@RequestParam("id") Integer id,@RequestParam("buildingId") String buildingId, @RequestParam("floorId") String floorId,
                                     @RequestParam("roomId") String roomId, @RequestParam("studentName") String studentName,HttpSession session){

        Map<String,String> map=new HashMap<>();
        Room room=new Room();
        room.setId(id);
        room.setBuildingId(buildingId);
        room.setFloorId(floorId);
        room.setRoomId(roomId);
        room.setStudentName(studentName);
        try{
            roomService.update(room);
            map.put("success","true");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","请核对信息确保登录名唯一");
        }
        return map;
    }

    /***
     * 删除房间信息返回map形式
     *
     */
    @PostMapping(value = "/remove_room")//删除房间记录
    @ResponseBody
    public Map<String,String> removeRooms(@RequestParam("id") Integer id,HttpSession session){
        Map<String,String> result = new HashMap<>();
        if(StringUtils.isEmpty(String.valueOf(id))){
            result.put("msg","当前房间id为空");
            return result;
        }
        try{
            roomService.delete(id);
            result.put("success","true");
            System.out.println("删除Id: "+id);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","Some errors occured.");
        }
        return result;
    }

}
