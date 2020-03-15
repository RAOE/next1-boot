package com.nextone.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-07-25
 * @description 菜单
 **/
@Setter
@Getter
@ToString
@Table(name = "sys_menu")
public class SysMenu {
    private static final long serialVersionUID = 1L;
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private String orderNum;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 类型:0目录,1菜单,2按钮
     */
    private String menuType;
    /**
     * 菜单状态:0显示,1隐藏
     */
    private String visible;
    /**
     * 权限字符串
     */
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 创建时间
     **/
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 子菜单
     */
    private List<SysMenu> children = new ArrayList<SysMenu>();
}
