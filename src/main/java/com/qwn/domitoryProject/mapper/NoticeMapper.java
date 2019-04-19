package com.qwn.domitoryProject.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qwn.domitoryProject.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 根据分类id获取通知信息
     *
     * @param classifyId
     * @return
     */
    List<Map> selectListByClassIfy(@Param("classifyId") Integer classifyId);

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
