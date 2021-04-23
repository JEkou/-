package com.example.service;

import com.example.entity.Asset;
import com.example.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 获取没有发送消息的列表
     * @return 没有发送消息的列表
     */
    public List<Asset> getNotSendAssetMessage() {
        List<Asset> notSendAssetsList = assetRepository.getByIsSendAndIsDelete(0, 0);
        return notSendAssetsList;
    }

    /**获取没有发送消息的
     * 获取没有发送消息的数量
     * @return 没有发送消息的数量
     */
    public int getNotSendAssetMessageCount() {
        return getNotSendAssetMessage().size();
    }

    /**
     * 保存从硬件发送过来的仪器信息
     * @param assetList 仪器信息
     * @return 保存的结果。保存成功则为消息的数量；失败返回-1
     */
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
