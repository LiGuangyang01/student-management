package com.example.shiro;

import com.example.entity.User;
import com.example.service.StuUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm{

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        return null;
    }

    @Autowired
    private StuUserService userSerivce;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken)arg0;

        User user = userSerivce.findByUsername(token.getUsername());

        return user==null? null: new SimpleAuthenticationInfo("",user.getPassword(),"");
    }

}