package com.example.dto;

import com.example.entity.Asset;
import com.example.entity.Localize;
import com.example.entity.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 */
public class ReportDTO {

    private String head;
    private String ip;
    private Integer length;
    private String myKey;
    List<LocalizeDTO> localizeList;

    public ReportDTO() {
    }
    public ReportDTO(Report report) {
        this.head = report.getHead();
        this.ip = report.getIp();
        this.length = report.getLength();

        List<LocalizeDTO> localizeDTOList = new ArrayList<>();
        for(Localize localize : report.getLocalizeList()){
            LocalizeDTO localizeDTO = new LocalizeDTO(localize);
            localizeDTOList.add(localizeDTO);
        }
        this.localizeList = localizeDTOList;

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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getMyKey() {
        return myKey;
    }

    public void setMyKey(String myKey) {
        this.myKey = myKey;
    }

    public List<LocalizeDTO> getLocalizeList() {
        return localizeList;
    }

    public void setLocalizeList(List<LocalizeDTO> localizeList) {
        this.localizeList = localizeList;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "head='" + head + '\'' +
                ", ip='" + ip + '\'' +
                ", length=" + length +
                ", myKey='" + myKey + '\'' +
                ", localizeList=" + localizeList +
                '}';
    }
}
