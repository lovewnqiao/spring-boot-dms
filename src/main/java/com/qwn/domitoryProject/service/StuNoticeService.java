package com.qwn.domitoryProject.service;

import com.qwn.domitoryProject.entity.Notice;
import com.qwn.domitoryProject.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StuNoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    public Notice detail(Integer id) {
        return noticeMapper.selectById(id);
    }

    public List<Map> list(Integer classifyId, String title) {
        return noticeMapper.selectListByClassIfy(classifyId, title);
    }
}
