package com.thyme;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThymeBootApplicationTests {

    @Test
    public void uuidTest() {

//        String replace = UUID.randomUUID().toString().replace("-", "");
//        System.out.println(replace);
//        String replace1 = UUID.randomUUID().toString().replace("-", "");
//        System.out.println(replace1);


        String s = DigestUtil.sha256Hex("123");
        System.out.println(s);
        String encode = new BCryptPasswordEncoder().encode(s);

        System.out.println(new BCryptPasswordEncoder().matches("a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",encode));

    }

}
