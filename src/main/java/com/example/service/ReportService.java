package com.example.service;

import com.example.entity.Report;
import com.example.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author USER
 */
@Service
public class ReportService {

    private ReportRepository reportRepository;

    @Autowired
    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int saveReport(Report report) {
        Report save = reportRepository.save(report);
        return save == null?-1:1;
    }
}
