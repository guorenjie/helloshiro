package com.guorenjie.shirospringboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author guorenjie
 * @date 2020/6/4
 */
@Data
public class User implements Serializable {
    // 用户id
    private Long id;
    // 登录名
    private String username;
    // 用户昵称
    private String nickname;
    
    private String password;

    //盐
    private String solt;
    // 创建时间
    private Date createtime;

    //用户状态：0=正常；1=禁用；2=锁定
    private int status;
    // 修改时间
    private Date updatetime;
    //用户所有角色值，用于shiro做角色权限的判断
    private Set<Role> roles = new HashSet<>();
}
