package com.example.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 祥玉
 */
@Entity(name = "config")
public class Config extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "local_port")
    private int localPort;

    @Column(name = "cloud_address")
    private String cloudAddress;

    @Column(name = "cloud_port")
    private int cloudPort;

    @Column(name = "frequency")
    private int frequency;

    @Column(name = "my_key")
    private String myKey;

    @Column(name = "version_number")
    private String versionNumber;

    @Column(name = "is_Delete")
    public int isDelete;

    @Column(name = "create_date")
    protected Date createDate;

    @Column(name = "update_date")
    protected Date updateDate;

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

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public String getCloudAddress() {
        return cloudAddress;
    }

    public void setCloudAddress(String cloudAddress) {
        this.cloudAddress = cloudAddress;
    }

    public int getCloudPort() {
        return cloudPort;
    }

    public void setCloudPort(int cloudPort) {
        this.cloudPort = cloudPort;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getMyKey() {
        return myKey;
    }

    public void setMyKey(String myKey) {
        this.myKey = myKey;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

}
