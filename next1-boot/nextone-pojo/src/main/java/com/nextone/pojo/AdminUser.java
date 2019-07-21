package com.nextone.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-05-08
 * @description
 **/
@Table(name = "t_adminusers")
public class AdminUser {
    private Long id;
    private String username;
    private String password;
    private String salt;
    @Column(name = "isEnable")
    private Integer isEnable;
    @Column(name = "last_login")
    private Date lastLogin;
    private String phone;
    private String nickname;
    @Column(name="createTime")
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", isEnable=" + isEnable +
                ", lastLogin=" + lastLogin +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
