package com.example.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author 祥玉
 */
@Entity(name = "localizes")
public class Localize extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "localize_id")
    private String localizeId;

    @Column(name = "localize_status")
    private int localizeStatus;

    @Column(name = "length")
    private int length;

    @ManyToOne(targetEntity = Report.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    @OneToMany(mappedBy = "localize",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Asset> assetList;

    @Column(name = "is_Delete")
    public int isDelete;

    @Column(name = "create_date")
    protected Date createDate;

    @Column(name = "update_date")
    protected Date updateDate;

    public Localize() {
    }

    public Localize(String localizeId, int localizeStatus, int length, List<Asset> assetList) {
        this.localizeId = localizeId;
        this.localizeStatus = localizeStatus;
        this.length = length;
        this.assetList = assetList;
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

    public String getLocalizeId() {
        return localizeId;
    }

    public void setLocalizeId(String localizeId) {
        this.localizeId = localizeId;
    }

    public int getLocalizeStatus() {
        return localizeStatus;
    }

    public void setLocalizeStatus(int localizeStatus) {
        this.localizeStatus = localizeStatus;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<Asset> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
    }

    @Override
    public String toString() {
        return "Localize{" +
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
        if (!(o instanceof Localize)) {
            return false;
        }
        Localize localize = (Localize) o;
        return getLocalizeStatus() == localize.getLocalizeStatus() &&
                getLength() == localize.getLength() &&
                Objects.equals(getLocalizeId(), localize.getLocalizeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocalizeId(), getLocalizeStatus(), getLength());
    }
}
