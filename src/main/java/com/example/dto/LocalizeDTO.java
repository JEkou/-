package com.example.dto;

import com.example.entity.Asset;
import com.example.entity.Localize;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author USER
 */
public class LocalizeDTO {

    private String localizeId;
    private Integer localizeStatus;
    private Integer length;
    List<AssetDTO> assetList;

    public LocalizeDTO() {
    }

    public LocalizeDTO(Localize localize){
        this.localizeId = localize.getLocalizeId();
        this.localizeStatus = localize.getLocalizeStatus();
        this.length = localize.getLength();

        List<AssetDTO> assetDTOList = new ArrayList<>();
        List<Asset> assetList = localize.getAssetList();

        for (Asset asset : assetList) {
            if(asset.getIsSend() == 0 & asset.getIsDelete() == 0) {
                AssetDTO assetDTO = new AssetDTO(asset);
                assetDTOList.add(assetDTO);
            }
        }
        this.assetList = assetDTOList;
    }

    public String getLocalizeId() {
        return localizeId;
    }

    public void setLocalizeId(String localizeId) {
        this.localizeId = localizeId;
    }

    public Integer getLocalizeStatus() {
        return localizeStatus;
    }

    public void setLocalizeStatus(Integer localizeStatus) {
        this.localizeStatus = localizeStatus;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<AssetDTO> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<AssetDTO> assetList) {
        this.assetList = assetList;
    }

    @Override
    public String toString() {
        return "LocalizeDTO{" +
                "localizeId='" + localizeId + '\'' +
                ", localizeStatus=" + localizeStatus +
                ", length=" + length +
                ", assetList=" + assetList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocalizeDTO that = (LocalizeDTO) o;
        return Objects.equals(localizeId, that.localizeId) && Objects.equals(localizeStatus, that.localizeStatus) && Objects.equals(length, that.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localizeId, localizeStatus, length);
    }
}
