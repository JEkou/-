package com.example.controller;

import com.example.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author USER
 */
@RestController
public class MessageController {

    private AssetsService assetsService;

    @Autowired
    public void setAssetsService(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    @PostMapping(value = "/getMessageCount")
    public int getNotSendMessageCount(){
        return assetsService.getNotSendAssetMessageCount();
    }
}
