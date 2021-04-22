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

    @GetMapping(value = "/getConfig")
    public Config getConfig() {
        return systemService.getSystemConfig();
    }

    @PostMapping(value = "/get")
    public List<Asset> setFrequency() {
        return assetsService.getNotSendAssetMessage();
    }

    @GetMapping("/operationSystemInfo")
    public String operationSystemInfo() {
        Properties props = System.getProperties();
        String osInfo = props.getProperty("os.name");
        return osInfo;
    }

    @GetMapping("/systemVersionNumber")
    public String systemVersionNumber(){
        return systemService.getSystemVersionNumber();
    }


    @PostMapping("/saveConfig")
    public int saveConfig(Config config){
        return systemService.saveConfig(config);
    }

    @GetMapping("/isConnect")
    public boolean connect(){
        String cloudAddress = systemService.getSystemConfig().getCloudAddress();
        return ConnectUtil.isConnect(cloudAddress);
    }

}
