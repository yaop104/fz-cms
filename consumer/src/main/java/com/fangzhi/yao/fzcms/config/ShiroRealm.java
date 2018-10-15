package com.fangzhi.yao.fzcms.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fangzhi.yao.fzcms.dto.UserInfo;
import com.fangzhi.yao.fzcms.entity.Permission;
import com.fangzhi.yao.fzcms.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "1.0.0")
    private IUserService iUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        logger.info("权限配置----->ShiroRealm.doGetAuthorizationInfo()");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        authorizationInfo.addRole(userInfo.getRoleInfo().getRoleCode());
        for(Permission p:userInfo.getRoleInfo().getPermissions()){
            authorizationInfo.addStringPermission(p.getPermissionCode());
        }

        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        logger.info("ShiroRealm.doGetAuthenticationInfo()");

        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = iUserService.findUserInfo(username);

        logger.info("----->userInfo=" + userInfo);
        if(userInfo == null){
            throw new AccountException();
        }else if(userInfo.getState() == 0){
            throw new DisabledAccountException();
        }else if(userInfo.getState() == 2){
            throw new LockedAccountException();
        }

        //保存登录用户ID
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("loginUserId",userInfo.getId());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户信息
                userInfo.getPassWord(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );

        return authenticationInfo;
    }

}