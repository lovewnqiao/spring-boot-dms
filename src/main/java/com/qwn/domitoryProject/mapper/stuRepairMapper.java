package com.qwn.domitoryProject.mapper;

import com.qwn.domitoryProject.entity.StuRepair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface stuRepairMapper {

    // 学生查询全部维修记录
    List<StuRepair> selectStuRepairByName(@Param("applicant") String applicant);

    //学生新增维修记录
    int addStuRepair(@Param("buildingId") String buildingId, @Param("floorId") String floorId,
                  @Param("roomId") String roomId, @Param("name") String name, @Param("descr") String descr,
                  @Param("data") String data, @Param("applicant") String applicant);

    //学生修改维修记录
    int updateByPrimaryKeyStuRepair(StuRepair stuRepair);

    //学生删除维修记录
    int deleteByPrimaryKeyStuRepair(Integer Id);
    //学生查询维修记录数量
    int getStuRepairnumber();


}
