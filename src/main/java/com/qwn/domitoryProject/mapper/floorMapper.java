package com.qwn.domitoryProject.mapper;

import com.qwn.domitoryProject.entity.Floor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface floorMapper {

    // 查询全部楼层记录
    List<Floor> selectAllFloor();

    //新增楼层记录
    int addFloor(@Param("buildingId") String buildingId, @Param("floorId") String floorId,
                 @Param("name") String name);

    //修改楼层记录
    int updateByPrimaryKeyFloor(Floor floor);

    //删除楼层记录
    int deleteByPrimaryKeyFloor(Integer Id);
    //查询楼层记录数量
    int getFloornumber();


}
