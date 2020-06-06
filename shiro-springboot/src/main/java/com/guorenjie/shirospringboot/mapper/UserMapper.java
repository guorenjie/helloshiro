package com.guorenjie.shirospringboot.mapper;

import com.guorenjie.shirospringboot.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author guorenjie
 * @date 2020/6/5
 */
@Repository
public interface UserMapper {

    /**
     * 通过username查找user
     * @param username
     * @return
     */
    public User findUserByName(String username);

    /**
     * 根据userid查找role
     * @param userid
     * @return
     */
    public Set<String> findRoleByUser(long userid);

    /**
     * 根据userid查找perm
     * @param userid
     * @return
     */
    public Set<String> findPermByUser(long userid);
}
