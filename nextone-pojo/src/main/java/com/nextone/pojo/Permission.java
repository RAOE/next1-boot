package com.nextone.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-08
 * @description
 **/
@Table(name="t_permissions")
public class Permission {
    @Id
    @Column(name="id")
    private Long id ;
    @Column(name="path")
    private String path;
    @Column(name="description")
    private String description;
    @Column(name="isDeleted")
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", description='" + description + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
