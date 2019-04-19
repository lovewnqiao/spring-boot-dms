package com.qwn.domitoryProject.mapper;

import com.qwn.domitoryProject.entity.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface reportMapper {

    // 查询学生入住记录
    List<Report> selectAllReport();

    //新增学生入住记录
    int addReport(@Param("studentId") String studentId,@Param("studentName") String studentName,
                  @Param("classes") String classes, @Param("telephone") String telephone,
                  @Param("dormitory") String dormitory, @Param("data") String data);

    //按照条件查询学生入住信息
    List<Report> pagingQueryReport(@Param("studentName") String studentName,@Param("classes")  String classes,@Param("dormitory")  String dormitory,@Param("data")  String data);

    //修改学生入住记录
    int updateByPrimaryKeyReport(Report report);

    //删除学生入住记录
    int deleteByPrimaryKeyReport(Integer Id);
    //查询学生入住记录数量
    int getReportnumber();


}
