package com.guorenjie.shirospringboot.realm;

import com.guorenjie.shirospringboot.entity.User;
import com.guorenjie.shirospringboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 自定义reaml
 *
 * @author guorenjie
 * @date 2020/6/4
 */
@Slf4j
public class UserReaml extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User)principalCollection.getPrimaryPrincipal();
        Set<String> roles = userService.findRoleByUser(user.getId());
        Set<String> perms = userService.findPermByUser(user.getId());
        info.addStringPermissions(perms);
        info.addRoles(roles);
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUserByName(token.getUsername());
        //密码加盐在md5
        String password = new Md5Hash(token.getPassword(),user.getSolt()).toString();
        if(user == null){
            throw new UnknownAccountException("账号不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new IncorrectCredentialsException("密码不正确");
        }
        if (user.getStatus()==1) {
            throw new DisabledAccountException("账号已禁用");
        }
        if (user.getStatus()==2) {
            throw new LockedAccountException("账号已锁定");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, token.getCredentials(), getName());
        return info;
    }
}
