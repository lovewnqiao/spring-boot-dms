package com.qwn.domitoryProject.service;

import com.github.pagehelper.PageHelper;
import com.qwn.domitoryProject.entity.Report;
import com.qwn.domitoryProject.mapper.reportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    /**
     * 注入dao
     */
    @Autowired
    private reportMapper reportMapper;

    //查询学生入住列表
    public List<Report> selectAllReport(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return reportMapper.selectAllReport();
    }


    //按照条件查询学生入住信息
    public List<Report> selectReportByCondition(int pageNum, int pageSize, String studentName, String classes,  String dormitory,String data) {
        return reportMapper.pagingQueryReport(studentName, classes,dormitory,data);
    }

    //添加入住信息
    public int addReport(String studentId,String studentName,String classes,String telephone,String dormitory,String data){
        /**
         * 注意查看mapper中的注释
         */
        return reportMapper.addReport( studentId, studentName, classes, telephone, dormitory,data);
    }

    //通过id 删除入住记录
    public int delete(Integer key){

        return  reportMapper.deleteByPrimaryKeyReport(key);
    }
    //修改学生入住信息
    public int update(Report report){
        return reportMapper.updateByPrimaryKeyReport(report);
    }

    public int getReportNumber(){
        return reportMapper.getReportnumber();
    }


}
