package com.example.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author 祥玉
 */
@Entity(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "head")
    private String head;

    @Column(name = "ip")
    private String ip;

    @Column(name = "length")
    private int length;

    @OneToMany(mappedBy = "report",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Localize> localizeList;

    @Column(name = "is_delete")
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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Localize> getLocalizeList() {
        return localizeList;
    }

    public void setLocalizeList(List<Localize> localizeList) {
        this.localizeList = localizeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return getLength() == report.getLength() &&
                Objects.equals(getHead(), report.getHead()) &&
                Objects.equals(getIp(), report.getIp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHead(), getIp(), getLength());
    }

    @Override
    public String toString() {
        return "Report{" +
                "head='" + head + '\'' +
                ", ip='" + ip + '\'' +
                ", length=" + length +
                ", localizeList=" + localizeList +
                '}';
    }
}
