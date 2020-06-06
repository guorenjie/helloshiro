

# Spring boot整合Shiro

## 添加依赖

spring-boot-starter-jdbc(实现对数据库连接池HikariCP的自动化配置)

mysql-connector-java

mybatis-spring-boot-starter

thymeleaf

shiro-spring

## 配置application.yaml

```yaml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      minimum-idle: 10 # 池中维护的最小空闲连接数，默认为 10 个。
      maximum-pool-size: 10 # 池中最大连接数，包括闲置和使用中的连接，默认为 10 个

mybatis:
  type-aliases-package: com.qiu.pojo
  mapper-locations: classpath:mapper/*.xml
```


## 自定义Reaml 

UserReaml

```java

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

//自定义的一个realm,需要继承一个
public class UserRealm  extends AuthorizingRealm {
    @Override
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        //拿到当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User)subject.getPrincipal();
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    @Override
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        User user = new User("1","root","root","user:add");
        UsernamePasswordToken userToken = (UsernamePasswordToken)authenticationToken;
        if(!userToken.getUsername().equals(user.getUserName())){
            return null; //抛出异常 UnknownAccountException
        }

        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}


```

## 编写Shiro配置类

ShiroConfig

```java
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /*
     * Subject 用户
     * SecurityManager 管理所有用户
     * Realm 连接数据
     * */

    //ShiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        //添加shiro的内置过滤器
        /*
            anon: 无需认证就可访问
            authc：必须认证才能访问
            user：必须拥有记住我功能才能访问
            perms: 拥有对某个资源的权限才能访问
            roles:拥有某个角色权限才能访问
       */
        Map<String, String> filterMap = new LinkedHashMap<>();

        //授权
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

        //filterMap.put("/user/*","authc");

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录界面
        bean.setLoginUrl("/login");
        //设置未授权页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建Realm，需自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
```
授权详细写法

| 过滤器     | 说明                                                         | 示例                              |
| ---------- | ------------------------------------------------------------ | --------------------------------- |
| anon       | 没有参数，表示可以匿名使用                                   | /admins/**=anon                   |
| authc      | 表示需要认证(登录)才能使用，没有参数                         | /admins/user/**=authc             |
| roles      | 拥有某个角色权限才能访问，参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如：admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。 | /admins/user/**=roles[admin]      |
| perms      | 拥有对某个资源的权限才能访问，参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，相当于isPermitedAll()方法。 | /admins/user/**=perms[user:add:*] |
| rest       | 根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等。 | /admins/user/**=rest[post]        |
| port       | 根据请求的端口                                               | /admins/user/**=port[8081]        |
| authcBasic | 没有参数表示httpBasic认证                                    | /admins/user/**=authcBasic        |
| ssl        | ssl没有参数，表示安全的url请求，协议为https                  | /admins/user/**=ssl               |
| user       | user没有参数表示必须存在用户，当登入操作时不做检查           | /admins/user/**=user              |
|            |                                                              |                                   |

注：anon，authcBasic，auchc，user是认证过滤器，perms，roles，ssl，rest，port是授权过滤器

## 编写网页


## 编写controller

```java
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/login")
    public String login(String username,String password){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录方法
        try {
            subject.login(token);
            return "登录成功";
        }catch (UnknownAccountException e){
            return "用户名错误";
        }catch (IncorrectCredentialsException e){
            return "密码错误";
        }
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/user/add")
    public String userAdd(){
        return "userAdd";
    }

    @GetMapping("/user/index")
    public String userIndex(){
        return "userIndex";
    }

    @GetMapping("/noauth")
    public String noauth(){
        return "未授权访问";
    }

    @GetMapping("/logout")
    public String logout(){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "注销";
    }
}
```



