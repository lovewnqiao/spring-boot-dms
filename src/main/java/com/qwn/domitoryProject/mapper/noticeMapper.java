package com.qwn.domitoryProject.mapper;

import com.qwn.domitoryProject.entity.Classify;
import com.qwn.domitoryProject.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface noticeMapper {

    // 查询全部通知分类记录
    List<Notice> selectAllNotice();

    //新增通知分类记录
    int addNotice(Notice notice);

    //修改通知分类记录
    int updateByPrimaryKeyNotice(Notice notice);

    //删除通知分类记录
    int deleteByPrimaryKeyNotice(Integer Id);
    //查询通知分类记录数量
    int getNoticenumber();


    List<Notice> noticelistFindAll();
}

