package com.qwn.domitoryProject.service;

import com.github.pagehelper.PageHelper;
import com.qwn.domitoryProject.entity.Classify;
import com.qwn.domitoryProject.mapper.classifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifyService {
    /**
     * 注入dao
     */
    @Autowired
    private classifyMapper classifyMapper;

    //查询宿舍楼列表
    public List<Classify> selectAllClassify(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return classifyMapper.selectAllClassify();
    }

    //添加宿舍楼
    public int addClassify(String classifyName,String descr){
        /**
         * 注意查看mapper中的注释
         */
        return classifyMapper.addClassify(classifyName,descr);
    }

    //通过id 删除宿舍楼记录
    public int delete(Integer key){

        return  classifyMapper.deleteByPrimaryKeyClassify(key);
    }
    //宿舍楼用户
    public int update(Classify classify){
        return classifyMapper.updateByPrimaryKeyClassify(classify);
    }

    public int getClassifynumber(){
        return classifyMapper.getClassifynumber();
    }


}
