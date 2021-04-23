package com.example.service;

import com.example.repository.SystemRepository;
import com.example.entity.BaseEntity;
import com.example.entity.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author USER
 */
@Service
public class SystemService {

    private SystemRepository systemRepository;

    @Autowired
    public void setSystemRepository(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    /**
     * 获取系统的配置
     * @return 配置信息
     */
    public Config getSystemConfig() {
        Config config = new Config();
        List<Config> configList = systemRepository.getByIsDelete(BaseEntity.NOTDELETE);
        if(configList.size() != 0){
            config = configList.get(0);
        }
        return config;
    }

    /**
     * 获取中转系统的版本号
     * @return 版本号
     */
    public String getSystemVersionNumber(){
        return getSystemConfig().getVersionNumber();
    }

    /**
     *
     * @param config 配置信息
     * @return 结果。
     */
    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int saveConfig(Config config){
        List<Config> notDeleteConfigList = systemRepository.getByIsDelete(BaseEntity.NOTDELETE);
        for (Config notDeleteConfig : notDeleteConfigList) {
            notDeleteConfig.setIsDelete(1);
            if(config.getVersionNumber() == null){
                config.setVersionNumber(notDeleteConfig.getVersionNumber());
            }
        }
        notDeleteConfigList.add(config);
        List<Config> saves =  systemRepository.saveAll(notDeleteConfigList);
        if(saves.size() == notDeleteConfigList.size()){
            return 1;
        }
        return 0;
    }
}
