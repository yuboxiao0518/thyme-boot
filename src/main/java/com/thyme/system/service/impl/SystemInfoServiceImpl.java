package com.thyme.system.service.impl;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.NumberUtil;
import com.thyme.common.utils.IpInfoUtils;
import com.thyme.system.entity.systeminfo.*;
import com.thyme.system.service.SystemInfoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * @author thyme
 * @ClassName SystemInfoServiceImpl
 * @Description TODO
 * @Date 2019/12/25 10:37
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Data
public class SystemInfoServiceImpl implements SystemInfoService {

    /**
     * 获取硬件信息
     */
    @Override
    public SystemHardwareInfo getSystemInfo() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        SystemHardwareInfo systemHardwareInfo = new SystemHardwareInfo();
        systemHardwareInfo.setCpuInfo(hal.getProcessor());
        systemHardwareInfo.setMemInfo(hal.getMemory());
        systemHardwareInfo.setSysInfo();
        systemHardwareInfo.setJvmInfo();
        systemHardwareInfo.setSysFiles(si.getOperatingSystem());
        return systemHardwareInfo;
    }


}
