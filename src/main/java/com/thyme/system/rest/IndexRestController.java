package com.thyme.system.rest;

import cn.hutool.core.util.IdUtil;
import com.thyme.system.service.RedisService;
import com.thyme.system.vo.ImgResult;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thyme
 * @ClassName IndexRestController
 * @Description TODO
 * @Date 2019/12/17 20:18
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexRestController {

    private final RedisService redisService;

    /**
     * 验证码 宽度
     */
    @Value("${loginCode.width}")
    private Integer width;

    /**
     * 验证码 高度
     */
    @Value("${loginCode.height}")
    private Integer height;

    /**
     * 验证码 运算位数
     */
    @Value("${loginCode.digit}")
    private Integer digit;

    /**
     * 获取验证码
     */
    @GetMapping("/code")
    public ImgResult getCode() {
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(width, height,digit);
        // 获取运算的结果
        String result = captcha.text();
        String uuid = IdUtil.simpleUUID();
        redisService.saveCode(uuid,result);
        return new ImgResult(captcha.toBase64(),uuid);
    }
}
