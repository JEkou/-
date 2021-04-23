package com.example.task;

import com.example.dto.ReportDTO;
import com.example.entity.Asset;
import com.example.entity.Config;
import com.example.service.AssetsService;
import com.example.service.SystemService;
import com.example.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author USER
 */

@Component
@EnableScheduling
public class SendSchedule implements SchedulingConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private SystemService systemService;
    private AssetsService assetsService;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Autowired
    public void setAssetsService(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    /**
     * 向与服务器发送数据
     * @param scheduledTaskRegistrar 定时任务注册对象
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {


        scheduledTaskRegistrar.addTriggerTask(() -> {
            //每次调用定时任务需要执行的事情
            System.out.println(format.format(new Date()) + ": -----------------timer------------");
            Config config = systemService.getSystemConfig();

            //String url = "http://192.168.1.13:8080/get";
            String url = "http://" + config.getCloudAddress() + ":" + config.getCloudPort();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            List<Asset> assetList = assetsService.getNotSendAssetMessage();
            List<ReportDTO> reportDTOList = MessageUtil.parseReport(assetList);
            for (ReportDTO reportDTO : reportDTOList) {
                reportDTO.setMyKey(config.getMyKey());
            }

            try {
                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(reportDTOList, httpHeaders);

                RestTemplate template = new RestTemplate();
                String result = template.postForObject(url, request, String.class);
                if(MessageUtil.isReceived(result)){
                    for (Asset asset : assetList) {
                        //此处的“1”应该换成常量，不该直接就写1
                        asset.setIsSend(1);
                    }
                    assetsService.saveAllAsset(assetList);
                }
            }catch (Exception e){
                logger.info("服务端没有接收到");
            }

        }, triggerContext -> {
            Config config = systemService.getSystemConfig();

            int frequency = 5;
            if (config.getFrequency() != 0) {
                frequency = config.getFrequency();
            }

            PeriodicTrigger periodicTrigger;
            //设置任务下次开始的时间间隔(ms)
            periodicTrigger = new PeriodicTrigger(frequency * 1000);
            Date nextExecutionTime = periodicTrigger.nextExecutionTime(triggerContext);

            return nextExecutionTime;
        });
    }

}
