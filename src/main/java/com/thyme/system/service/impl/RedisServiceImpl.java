package com.thyme.system.service.impl;

import com.thyme.system.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author thyme
 * @ClassName RedisServiceImpl
 * @Description TODO
 * @Date 2019/12/17 20:09
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SuppressWarnings({"unchecked","all"})
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate redisTemplate;

    @Value("${loginCode.expiration}")
    private Long expiration;

    @Override
    public String getCodeVal(String key) {
        try {
            return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public void saveCode(String key, Object val) {
        redisTemplate.opsForValue().set(key,val);
        redisTemplate.expire(key,expiration, TimeUnit.MINUTES);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}