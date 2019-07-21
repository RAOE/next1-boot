package com.nextone.shiro;

import com.nextone.pojo.AdminUser;
import com.nextone.pojo.Permission;
import com.nextone.pojo.Role;
import com.nextone.service.AdminUserService;
import com.nextone.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-08
 * @description
 **/
public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private RoleService roleService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录的用户名
        String currentUsername = super.getAvailablePrincipal(principals).toString();
        //从数据库中获取当前登录用户的详细信息
        AdminUser adminUser = adminUserService.findByUserName(currentUsername);
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        if (adminUser != null) {
            //在这里添加的role对应的就是权限的名，比如说，你给一个url添加了roles[管理员]，有个叫mopoint的用户登录了，
            //他想要访问这个url，那么在这里就需要给他赋予管理员这个权限，也就是说
            //这里面simpleAuthorInfo.addRole("管理员");加上的就是管理员三个字。
            Set<String> roleNames = roleService.queryAllRolesByUsername(adminUser);
            Set<String> permissions = roleService.queryAllPermissionsByUsername(adminUser);
            if (roleNames != null && !roleNames.isEmpty() && roleNames.size() > 0) {
                for (String roleName : roleNames) {
                    simpleAuthorInfo.addRole(roleName);
                }
            }
            if (permissions != null && !permissions.isEmpty() && permissions.size() > 0) {
                for (String permission : permissions) {
                    simpleAuthorInfo.addStringPermission(permission);
                }
            }
            //  logger.info(adminUser.getUsername() + "  权限认证通过");
            return simpleAuthorInfo;
        } else {
            //如果返回空表示用户访问失败，会自动跳转到刚才unauthorizedUrl指定的地址。配置在shiro.xml里面
            return null;
        }
    }

    /**
     * 登陆认证
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getPrincipal().toString();
        AdminUser user = adminUserService.findByUserName(username);
        if (user != null) {
//将查询到的用户账号和密码存放到 authenticationInfo用于后面的权限判断。第三个参数随便放一个就行了。
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), this.getName());
            return authenticationInfo;
        } else {
            return null;
        }


    }
}
