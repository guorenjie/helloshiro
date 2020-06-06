package com.guorenjie.shirospringboot.entity;


import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Role {

  private long id;
  private String rolename;
  //角色所有权限值，用于shiro做资源权限的判断
  private Set<Perm> perms = new HashSet<>();

}
