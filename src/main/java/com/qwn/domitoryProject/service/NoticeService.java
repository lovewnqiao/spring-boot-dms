package com.qwn.domitoryProject.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.qwn.domitoryProject.entity.Notice;
import com.qwn.domitoryProject.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    /**
     * 分页查询
     *
     * @param classifyId
     * @return
     */
    public List<Map> pageList(Integer classifyId) {
      return   noticeMapper.selectListByClassIfy(classifyId);
//        Wrapper<Notice> wrapper = new EntityWrapper<>();
//        if (classifyId != null) {
//            wrapper.eq("classify_id", classifyId);
//        }
//        return noticeMapper.selectList(wrapper);
    }

    /**
     * 通知数量
     *
     * @return
     */
    public int noticeCount(Integer classifyId){
        Wrapper<Notice> wrapper = new EntityWrapper<>();
        if (classifyId != null) {
            wrapper.eq("classify_id", classifyId);
        }
       return noticeMapper.selectCount(wrapper);
    }
}
