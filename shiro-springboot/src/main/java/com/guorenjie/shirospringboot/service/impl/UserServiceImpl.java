package com.guorenjie.shirospringboot.service.impl;

import com.guorenjie.shirospringboot.entity.User;
import com.guorenjie.shirospringboot.mapper.UserMapper;
import com.guorenjie.shirospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author guorenjie
 * @date 2020/6/5
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public Set<String> findRoleByUser(long userid) {
        return userMapper.findRoleByUser(userid);
    }

    @Override
    public Set<String> findPermByUser(long userid) {
        return userMapper.findPermByUser(userid);
    }
}
