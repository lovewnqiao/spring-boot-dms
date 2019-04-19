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
}
