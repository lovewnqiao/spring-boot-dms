package com.qwn.domitoryProject.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_notice")
public class Notice {
    private Integer id;
    private Integer classifyId;
    private String title;
    private String content;
    private String applicant;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getDate() {
        return date;
    }

    public Notice setDate(String date) {
        this.date = date;
        return this;
    }
}
