package com.blog.realm;

import com.blog.entity.Blogger;
import com.blog.service.BloggerService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/17
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private BloggerService bloggerService;

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        try {
            //获取用户名
            String userName = (String) authenticationToken.getPrincipal();
            //查询用户
            Blogger blogger = bloggerService.findBloggerByUserName(userName);
            //如果不为空，表示用户存在，
            if(blogger!=null){
                //返回认证对象
                return new SimpleAuthenticationInfo(blogger.getUserName(),blogger.getPassword(),"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;//登录失败
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

}
