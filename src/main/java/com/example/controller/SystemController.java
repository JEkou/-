package com.example.controller;

import com.example.dto.ReportDTO;
import com.example.entity.Asset;
import com.example.entity.Config;
import com.example.service.AssetsService;
import com.example.service.SystemService;
import com.example.util.ConnectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * @author USER
 */
@RestController
public class SystemController {

    private SystemService systemService;
    private AssetsService assetsService;

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Autowired
    public void setAssetsService(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    /**
     * 获取系统的配置信息。
     * @return 包含配置信息的对象
     */
    @GetMapping(value = "/getConfig")
    public Config getConfig() {
        return systemService.getSystemConfig();
    }

    /**
     * 获取操作系统的信息
     * @return 字符串类型的操作系统信息。主要是操作系统名称。
     */
    @GetMapping("/operationSystemInfo")
    public String operationSystemInfo() {
        Properties props = System.getProperties();
        String osInfo = props.getProperty("os.name");
        return osInfo;
    }

    /**
     * 获取运行平台的操作系统的版本号
     * @return 操作系统版本号
     */
    @GetMapping("/systemVersionNumber")
    public String systemVersionNumber(){
        return systemService.getSystemVersionNumber();
    }

    /**
     * 将前端传递过来的配置信息保存到本队数据库
     * @param config 前端输入的配置信息
     * @return 保存结果。保存成功反返回值大于0，否则为-1
     */
    @PostMapping("/saveConfig")
    public int saveConfig(Config config){
        return systemService.saveConfig(config);
    }

    /**
     * 检查与与服务器之间的连接情况
     * @return 与服务器是否连通
     */
    @GetMapping("/isConnect")
    public boolean connect(){
        String cloudAddress = systemService.getSystemConfig().getCloudAddress();
        return ConnectUtil.isConnect(cloudAddress);
    }

}
