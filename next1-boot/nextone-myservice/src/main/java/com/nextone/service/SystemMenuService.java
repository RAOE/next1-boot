package com.nextone.service;


import com.nextone.pojo.SysMenu;

import java.util.List;

public interface SystemMenuService {
    /**
     * 查询出所有的系统菜单信息
     * @return
     */
    List<SysMenu> selectAllMenus();
}
