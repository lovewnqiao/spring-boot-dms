package com.qwn.domitoryProject.service;

import com.github.pagehelper.PageHelper;
import com.qwn.domitoryProject.entity.StuRepair;
import com.qwn.domitoryProject.mapper.stuRepairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuRepairService {
    /**
     * 注入dao
     */
    @Autowired
    private stuRepairMapper stuRepairMapper;

    //查询用户列表
    public List<StuRepair> selectStuRepairByName(int pageNum, int pageSize,String applicant){
        PageHelper.startPage(pageNum, pageSize);
        return stuRepairMapper.selectStuRepairByName(applicant);
    }

    //注册新用户
    public int addStuRepair(String buildingId,String flooorId,String roomId,String name,String descr,String data,String applicant){
        /**
         * 注意查看mapper中的注释
         */
        return stuRepairMapper.addStuRepair(buildingId,flooorId,roomId,name,descr,data,applicant);
    }

    //通过id 删除维修记录
    public int delete(Integer key){

        return  stuRepairMapper.deleteByPrimaryKeyStuRepair(key);
    }
    //修改用户
    public int update(StuRepair stuRepair){
        return stuRepairMapper.updateByPrimaryKeyStuRepair(stuRepair);
    }

    public int getStuRepairnumber(){
        return stuRepairMapper.getStuRepairnumber();
    }


}
