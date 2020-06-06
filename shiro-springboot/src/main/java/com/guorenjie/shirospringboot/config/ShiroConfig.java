package com.guorenjie.shirospringboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.guorenjie.shirospringboot.realm.UserReaml;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * @author guorenjie
 * @date 2020/6/4
 */
@Configuration
public class ShiroConfig {



    @Bean(name="shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        /*
            设置过滤器
            anon: 无需认证就可访问
            authc：必须认证才能访问
            user：必须拥有记住我功能才能访问
            perms: 拥有对某个资源的权限才能访问
            roles:拥有某个角色权限才能访问
       */
        Map<String,String> map = new LinkedHashMap<>();
        map.put("/", "anon");
        map.put("/user/toLogin", "anon");
        map.put("/user/ToRegister", "anon");
        map.put("/user/toError", "anon");
        map.put("/user/login", "anon");
        map.put("/user/register", "anon");
        map.put("/user/error", "anon");
        map.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(map);
        //设置登录页
        bean.setLoginUrl("/user/toLogin");
        //设置登陆成功跳转页
        bean.setSuccessUrl("/");
        //设置权限不足页
        bean.setUnauthorizedUrl("/user/toError");
        return bean;
    }

    @Bean(name="defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userReaml") UserReaml userReaml){
        DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
        sm.setRealm(userReaml);
        return sm;
    }

    @Bean(name ="userReaml")
    public UserReaml getUserReaml(){
        return new UserReaml();
    }

    /**
     * 配置ShiroDialect，用于Shiro和thymeleaf标签配合使用
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){

        return new ShiroDialect();
    }
}
