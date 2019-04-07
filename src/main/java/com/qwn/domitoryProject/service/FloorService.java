package com.qwn.domitoryProject.service;

import com.github.pagehelper.PageHelper;
import com.qwn.domitoryProject.entity.Floor;
import com.qwn.domitoryProject.mapper.floorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {
    /**
     * 注入dao
     */
    @Autowired
    private floorMapper floorMapper;

    //查询楼层列表
    public List<Floor> selectAllFloor(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return floorMapper.selectAllFloor();
    }

    //添加新楼层
    public int addFloor(String buildingId,String flooorId,String name){
        /**
         * 注意查看mapper中的注释
         */
        return floorMapper.addFloor(buildingId,flooorId,name);
    }

    //通过id 删除楼层记录
    public int delete(Integer key){

        return  floorMapper.deleteByPrimaryKeyFloor(key);
    }
    //修改楼层
    public int update(Floor floor){
        return floorMapper.updateByPrimaryKeyFloor(floor);
    }

    public int getUsernumber(){
        return floorMapper.getFloornumber();
    }


}
