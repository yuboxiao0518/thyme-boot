package com.thyme.system.rest;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.thyme.common.base.ApiResponse;
import com.thyme.system.entity.systeminfo.SystemHardwareInfo;
import com.thyme.system.service.SystemInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thyme
 * @ClassName SystemInfoController
 * @Description TODO
 * @Date 2019/12/25 11:08
 */
@RestController
@RequestMapping("/system")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SystemInfoController {

    private final SystemInfoService systemInfoService;

    /**
     * 获取硬件信息
     */
    @GetMapping("/getInfo")
    public ApiResponse getInfo(){
        SystemHardwareInfo systemInfo = systemInfoService.getSystemInfo();
        return ApiResponse.ofSuccess(systemInfo);
    }
}
