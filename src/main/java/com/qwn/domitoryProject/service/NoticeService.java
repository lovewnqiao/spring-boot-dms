package com.qwn.domitoryProject.service;

import com.qwn.domitoryProject.entity.Notice;
import com.qwn.domitoryProject.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    /**
     * 注入dao
     */
    @Autowired
    private NoticeMapper noticeMapper;

    //查询宿舍楼列表
    public List selectAllNotice(Integer classifyId, String title) {
        return noticeMapper.selectListByClassIfy(classifyId, title);
    }

    //添加宿舍楼
    public int addNotice(Notice notice) {
        /**
         * 注意查看mapper中的注释
         */
        return noticeMapper.insert(notice);
//        return noticeMapper.addNotice(notice);
    }

    //通过id 删除宿舍楼记录
    public int delete(Integer key) {

        return noticeMapper.deleteByPrimaryKeyNotice(key);
    }

    //宿舍楼用户
    public int update(Notice notice) {
        return noticeMapper.updateByPrimaryKeyNotice(notice);
    }

    public int getNoticenumber(Integer classifyId, String title) {
        return noticeMapper.getNoticenumber(classifyId, title);
    }


    public List<Notice> noticelistFindAll() {
        return noticeMapper.noticelistFindAll();
    }
}

