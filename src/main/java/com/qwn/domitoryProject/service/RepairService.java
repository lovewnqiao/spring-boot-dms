package com.qwn.domitoryProject.service;

import com.github.pagehelper.PageHelper;
import com.qwn.domitoryProject.entity.Repair;
import com.qwn.domitoryProject.mapper.repairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {
    /**
     * 注入dao
     */
    @Autowired
    private repairMapper repairMapper;

    //查询用户列表
    public List<Repair> selectAllRepair(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return repairMapper.selectAllRepair();
    }

    //注册新用户
    public int addRepair(String buildingId,String flooorId,String roomId,String name,String descr,String data,String applicant){
        /**
         * 注意查看mapper中的注释
         */
        return repairMapper.addRepair(buildingId,flooorId,roomId,name,descr,data,applicant);
    }

    //通过id 删除维修记录
    public int delete(Integer key){

        return  repairMapper.deleteByPrimaryKeyRepair(key);
    }
    //修改用户
    public int update(Repair repair){
        return repairMapper.updateByPrimaryKeyRepair(repair);
    }

    public int getUsernumber(){
        return repairMapper.getRepairnumber();
    }


}
