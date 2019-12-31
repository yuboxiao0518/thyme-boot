package com.thyme;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.thyme.system.dao.SysUserDao;
import com.thyme.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
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


    @Test
    public void testInvoicev1() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/json; charset=utf-8");
        headers.put("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCREE0RkRXeDQ5cWFyaTlyY1hsMDh6dWJrSzFORkxydiJ9.KH5Afj0s95NHDBAXiGxCKUNn1AT7z7NJuKc-xjQo2Ak");
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("image", FileUtil.file("E:\\1.jpg"));
        String url = "http://ai.aisino.com:28000/invoicev1";
        String body = HttpRequest.post(url)
                .form(paramMap)
                .addHeaders(headers)
                .execute().body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        System.out.println(jsonObject);
    }
}
