package com.qwn.domitoryProject.service;

import com.github.pagehelper.PageHelper;
import com.qwn.domitoryProject.entity.Notice;
import com.qwn.domitoryProject.mapper.noticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    /**
     * 注入dao
     */
    @Autowired
    private noticeMapper noticeMapper;

    //查询宿舍楼列表
    public List<Notice> selectAllNotice(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return noticeMapper.selectAllNotice();
    }

    //添加宿舍楼
    public int addNotice(Notice notice){
        /**
         * 注意查看mapper中的注释
         */
        return noticeMapper.addNotice(notice);
    }

    //通过id 删除宿舍楼记录
    public int delete(Integer key){

        return  noticeMapper.deleteByPrimaryKeyNotice(key);
    }
    //宿舍楼用户
    public int update(Notice notice){
        return noticeMapper.updateByPrimaryKeyNotice(notice);
    }

    public int getNoticenumber(){
        return noticeMapper.getNoticenumber();
    }


    public List<Notice> noticelistFindAll() {
        return noticeMapper.noticelistFindAll();
    }
}

