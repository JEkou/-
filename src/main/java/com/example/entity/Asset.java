package com.example.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author 祥玉
 */
@Entity(name = "assets")
public class Asset extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "rssI")
    private String rssI;

    @Column(name = "status")
    private String status;

    @Column(name = "volt")
    private String volt;

    @Column(name = "amp")
    private String amp;

    @Column(name = "current_power")
    private String currentPower;

    @Column(name = "rate_power")
    private String ratePower;

    @Column(name = "is_send")
    private int isSend;

    @ManyToOne(targetEntity = Localize.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "localize_id")
    private Localize localize;

    @Column(name = "is_delete")
    public int isDelete;

    @Column(name = "create_date")
    protected Date createDate;

    @Column(name = "update_date")
    protected Date updateDate;

    public Asset(){

    }

    public Asset(String assetId, String rssI, String status, String volt, String amp, String currentPower, String ratePower) {
        this.assetId = assetId;
        this.rssI = rssI;
        this.status = status;
        this.volt = volt;
        this.amp = amp;
        this.currentPower = currentPower;
        this.ratePower = ratePower;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getIsSend() {
        return isSend;
    }

    public void setIsSend(int isSend) {
        this.isSend = isSend;
    }

    public Localize getLocalize() {
        return localize;
    }

    public void setLocalize(Localize localize) {
        this.localize = localize;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetId='" + assetId + '\'' +
                ", rssI='" + rssI + '\'' +
                ", status='" + status + '\'' +
                ", volt='" + volt + '\'' +
                ", amp='" + amp + '\'' +
                ", currentPower='" + currentPower + '\'' +
                ", ratePower='" + ratePower + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Asset)) {
            return false;
        }
        Asset asset = (Asset) o;
        return getIsSend() == asset.getIsSend() &&
                getIsDelete() == asset.getIsDelete() &&
                Objects.equals(getAssetId(), asset.getAssetId()) &&
                Objects.equals(getRssI(), asset.getRssI()) &&
                Objects.equals(getStatus(), asset.getStatus()) &&
                Objects.equals(getVolt(), asset.getVolt()) &&
                Objects.equals(getAmp(), asset.getAmp()) &&
                Objects.equals(getCurrentPower(), asset.getCurrentPower()) &&
                Objects.equals(getRatePower(), asset.getRatePower());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssetId(), getRssI(), getStatus(), getVolt(), getAmp(), getCurrentPower(), getRatePower(), getIsSend(), getIsDelete());
    }
}
