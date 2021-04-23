package com.example.util;

import com.example.dto.ReportDTO;
import com.example.entity.Asset;

import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 */
public class MessageUtil {


    public static boolean isReceived(String result) {
        //以下是验证过程，验证不通过随时返回false

        return true;
    }

    public static List<ReportDTO> parseReport(List<Asset> notSendAssetsList) {
        List<ReportDTO> reportList = new ArrayList();
        for (Asset asset : notSendAssetsList) {
            ReportDTO reportDTO = new ReportDTO(asset.getLocalize().getReport());
            if (!reportList.contains(reportDTO)) {
                reportList.add(reportDTO);
            }
        }
        return reportList;
    }

}
