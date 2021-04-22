package com.example.service;

import com.example.dto.AssetDTO;
import com.example.dto.LocalizeDTO;
import com.example.dto.ReportDTO;
import com.example.entity.Asset;
import com.example.entity.Localize;
import com.example.entity.Report;
import com.example.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 */
@Service
public class AssetsService {

    private AssetRepository assetRepository;

    @Autowired
    public void setAssetRepository(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    public List<Asset> getNotSendAssetMessage() {
        List<Asset> notSendAssetsList = assetRepository.getByIsSendAndIsDelete(0, 0);
        return notSendAssetsList;
    }

    public int getNotSendAssetMessageCount() {
        return getNotSendAssetMessage().size();
    }

    @Modifying(flushAutomatically = true,clearAutomatically = true)
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int saveAllAsset(List<Asset> assetList){


        List<Asset> saveAll = assetRepository.saveAll(assetList);
        if(saveAll != null){
            return saveAll.size();
        }
        return -1;
    }


}
