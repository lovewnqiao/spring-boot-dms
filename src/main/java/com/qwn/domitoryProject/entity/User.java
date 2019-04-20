package com.qwn.domitoryProject.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_user_info")
public class User {

    private Integer id;
    private String userId;
    private String password;
    private String name;
    private String sex;
    private Integer age;
    private String telephone;
    private String building;
    private String floor;
    private Integer room;
    private Integer isAdmin;

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUserId() {return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getSex() {return sex;}

    public void setSex(String sex) {this.sex = sex;}

    public Integer getAge() {return age;}

    public void setAge(Integer age) {this.age = age;}

    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) {this.telephone = telephone;}

    public String getBuilding() {return building;}

    public void setBuilding(String building) {this.building = building;}

    public String getFloor() {return floor;}

    public void setFloor(String floor) {this.floor = floor;}

    public Integer getRoom() {return room;}

    public void setRoom(Integer room) { this.room = room; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}