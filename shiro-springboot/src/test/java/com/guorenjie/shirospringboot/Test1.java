package com.guorenjie.shirospringboot;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * @author guorenjie
 * @date 2020/6/6
 */
@Slf4j
@SpringBootTest
public class Test1 {

    @Test
    public void testuuid(){
        String uuid = UUID.randomUUID().toString();
        log.info(uuid);

    }
    @Test
    public void md5Hash(){
        String passowrd = new Md5Hash("123456","8ed4e692-2ce0-416c-876b-532105abf915").toString();
        log.info(passowrd);
    }
}
