package com.qwn.domitoryProject.mapper;

import com.qwn.domitoryProject.entity.Repair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface repairMapper {

    // 查询全部维修记录
    List<Repair> selectAllRepair();

    //新增维修记录
    int addRepair(@Param("buildingId") String buildingId, @Param("floorId") String floorId,
                  @Param("roomId") String roomId, @Param("name") String name, @Param("descr") String descr,
                  @Param("data") String data, @Param("applicant") String applicant);

    //按照条件查询维修记录
    List<Repair> pagingQueryRepair(@Param("buildingId") String buildingId,@Param("name")  String name,@Param("applicant")  String applicant,@Param("state")  String state);

    //修改维修记录
    int updateByPrimaryKeyRepair(Repair repair);

    //删除维修记录
    int deleteByPrimaryKeyRepair(Integer Id);
    //查询维修记录数量
    int getRepairnumber();


}
