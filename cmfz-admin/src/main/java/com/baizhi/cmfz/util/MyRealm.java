package com.baizhi.cmfz.util;

import com.baizhi.cmfz.dao.ManagerDao;
import com.baizhi.cmfz.entity.Manager;
import com.baizhi.cmfz.entity.SysPermission;
import com.baizhi.cmfz.entity.SysRole;
import com.baizhi.cmfz.service.ManagerService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

/**
 * Created by 阿斯加的酱油 on 2018/7/13.
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private  ManagerService managerService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String managerName = (String) principalCollection.getPrimaryPrincipal();

        List<SysRole> sysRoles = managerService.queryRoleByManagerName(managerName);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (SysRole sysRole : sysRoles) {
            //将角色标签保存到返回值
            info.addRole(sysRole.getRoleTag());
        }

        List<SysPermission> sysPermissions = managerService.queryPermissionByManagerName(managerName);
        for (SysPermission sysPermission : sysPermissions) {
            info.addStringPermission(sysPermission.getPermissionTag());
        }
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();

        Manager manager = managerDao.selectManager(username);

        if (manager != null) {
            return new SimpleAuthenticationInfo(manager.getMgrName(),
                    manager.getMgrPwd(),
                    ByteSource.Util.bytes(manager.getSalt()),
                    UUID.randomUUID().toString());
        }

        return null;
    }
}
