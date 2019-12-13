package com.thyme;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThymeBootApplicationTests {

    @Test
    public void uuidTest() {

        String replace = UUID.randomUUID().toString().replace("-", "");
        System.out.println(replace);
        String replace1 = UUID.randomUUID().toString().replace("-", "");
        System.out.println(replace1);

    }

}
