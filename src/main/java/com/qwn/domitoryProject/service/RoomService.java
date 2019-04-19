package com.qwn.domitoryProject.service;

import com.github.pagehelper.PageHelper;
import com.qwn.domitoryProject.entity.Room;
import com.qwn.domitoryProject.mapper.roomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    /**
     * 注入dao
     */
    @Autowired
    private roomMapper roomMapper;

    //查询房间列表
    public List<Room> selectAllRoom(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return roomMapper.selectAllRoom();
    }
    //按照条件查询房间记录
    public List<Room> selectRoomByCondition(int pageNum, int pageSize, String buildingId, String floorId,  String roomId,String studengName) {
        return roomMapper.pagingQueryRoom(buildingId, floorId,roomId,studengName);
    }
    //添加新房间
    public int addRoom(String buildingId,String flooorId,String roomId,String studentName){
        /**
         * 注意查看mapper中的注释
         */
        return roomMapper.addRoom(buildingId,flooorId,roomId,studentName);
    }

    //通过id 删除房间记录
    public int delete(Integer key){

        return  roomMapper.deleteByPrimaryKeyRoom(key);
    }
    //修改房间
    public int update(Room room){
        return roomMapper.updateByPrimaryKeyRoom(room);
    }

    public int getRoomNumber(){
        return roomMapper.getRoomnumber();
    }


}
