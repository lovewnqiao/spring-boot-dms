package com.qwn.domitoryProject.mapper;

import com.qwn.domitoryProject.entity.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface classifyMapper {

    // 查询全部通知分类记录
    List<Classify> selectAllClassify();

    //新增通知分类记录
    int addClassify(@Param("classifyName") String classifyName,
                    @Param("descr") String descr);

    //修改通知分类记录
    int updateByPrimaryKeyClassify(Classify classify);

    //删除通知分类记录
    int deleteByPrimaryKeyClassify(Integer Id);
    //查询通知分类记录数量
    int getClassifynumber();


}
