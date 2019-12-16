package com.thyme;

import com.thyme.system.dao.SysUserDao;
import com.thyme.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @author thyme
 * @ClassName MybatisTest
 * @Description TODO
 * @Date 2019/12/10 21:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MybatisTest {

    @Autowired
    private SysUserDao userDao;


    @Test
    public void insert() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userDao.insert(
                SysUser.builder()
                .id(uuid)
                .name("admin")
                .password("123")
                .createTime(new Date())
                .build()
        );
    }

    @Test
    public void password() {
        String encode = new BCryptPasswordEncoder().encode("123");
        System.out.println(encode);
    }

}
