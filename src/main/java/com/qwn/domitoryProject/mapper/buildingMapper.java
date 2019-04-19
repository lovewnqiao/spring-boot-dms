package com.qwn.domitoryProject.mapper;

import com.qwn.domitoryProject.entity.Building;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface buildingMapper {

    // 查询全部宿舍楼记录
    List<Building> selectAllBuilding();

    //新增宿舍楼记录
    int addBuilding(@Param("buildingId") String buildingId,
                  @Param("name") String name);

    //按照条件查询维修记录
    List<Building> pagingQueryBuilding(@Param("buildingId") String buildingId);

    //修改宿舍楼记录
    int updateByPrimaryKeyBuilding(Building building);

    //删除宿舍楼记录
    int deleteByPrimaryKeyBuilding(Integer Id);
    //查询宿舍楼记录数量
    int getBuildingnumber();


}
