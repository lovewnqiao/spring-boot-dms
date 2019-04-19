package com.qwn.domitoryProject.service;

import com.github.pagehelper.PageHelper;
import com.qwn.domitoryProject.entity.Building;
import com.qwn.domitoryProject.mapper.buildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
    /**
     * 注入dao
     */
    @Autowired
    private buildingMapper buildingMapper;

    //查询宿舍楼列表
    public List<Building> selectAllBuilding(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return buildingMapper.selectAllBuilding();
    }

    //按照条件查询宿舍楼记录
    public List<Building> selectBuildingByCondition(int pageNum, int pageSize, String buildingId) {
        return buildingMapper.pagingQueryBuilding(buildingId);
    }

    //添加宿舍楼
    public int addBuilding(String buildingId,String name){
        /**
         * 注意查看mapper中的注释
         */
        return buildingMapper.addBuilding(buildingId,name);
    }

    //通过id 删除宿舍楼记录
    public int delete(Integer key){

        return  buildingMapper.deleteByPrimaryKeyBuilding(key);
    }
    //宿舍楼用户
    public int update(Building building){
        return buildingMapper.updateByPrimaryKeyBuilding(building);
    }

    public int getBuildingNumber(){
        return buildingMapper.getBuildingnumber();
    }


}
