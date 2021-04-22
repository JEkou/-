package com.example.repository;

import com.example.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 祥玉
 */
@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
}
