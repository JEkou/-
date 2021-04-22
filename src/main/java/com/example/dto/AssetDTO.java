package com.example.dto;

import com.example.entity.Asset;
import com.example.entity.BaseEntity;

import java.util.Date;

/**
 * @author USER
 */
public class AssetDTO extends BaseEntity {

    private String assetId;
    private String rssI;
    private String status;
    private String volt;
    private String amp;
    private String currentPower;
    private String ratePower;

    public AssetDTO() {
    }

    public AssetDTO(Asset asset) {
        this.assetId = asset.getAssetId();
        this.rssI = asset.getRssI();
        this.status = asset.getStatus();
        this.volt = asset.getVolt();
        this.amp = asset.getAmp();
        this.currentPower = asset.getCurrentPower();
        this.ratePower = asset.getRatePower();
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getRssI() {
        return rssI;
    }

    public void setRssI(String rssI) {
        this.rssI = rssI;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVolt() {
        return volt;
    }

    public void setVolt(String volt) {
        this.volt = volt;
    }

    public String getAmp() {
        return amp;
    }

    public void setAmp(String amp) {
        this.amp = amp;
    }

    public String getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(String currentPower) {
        this.currentPower = currentPower;
    }

    public String getRatePower() {
        return ratePower;
    }

    public void setRatePower(String ratePower) {
        this.ratePower = ratePower;
    }

    @Override
    public String toString() {
        return "AssetDTO{" +
                "assetId='" + assetId + '\'' +
                ", rssI='" + rssI + '\'' +
                ", status='" + status + '\'' +
                ", volt='" + volt + '\'' +
                ", amp='" + amp + '\'' +
                ", currentPower='" + currentPower + '\'' +
                ", ratePower='" + ratePower + '\'' +
                '}';
    }
}
