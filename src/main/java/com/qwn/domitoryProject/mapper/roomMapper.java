package com.qwn.domitoryProject.mapper;

import com.qwn.domitoryProject.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface roomMapper {

    // 查询全部房间记录
    List<Room> selectAllRoom();

    //按照条件查询维修记录
    List<Room> pagingQueryRoom(@Param("buildingId") String buildingId,@Param("floorId")  String floorId,@Param("roomId")  String roomId,@Param("studentName")  String studentName);


    //新增房间记录
    int addRoom(@Param("buildingId") String buildingId, @Param("floorId") String floorId,
                  @Param("roomId") String roomId, @Param("studentName") String studentName);

    //修改房间记录
    int updateByPrimaryKeyRoom(Room room);

    //删除房间记录
    int deleteByPrimaryKeyRoom(Integer Id);
    //查询房间记录数量
    int getRoomnumber();


}
