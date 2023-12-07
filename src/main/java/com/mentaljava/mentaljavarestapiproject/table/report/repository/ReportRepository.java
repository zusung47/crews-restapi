package com.mentaljava.mentaljavarestapiproject.table.report.repository;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query("SELECT r FROM Report r WHERE r.reportCrew IS NULL")
    List<Report> findAllReportsWithoutCrew();

    @Query("SELECT r FROM Report r WHERE r.reportTarget IS NULL")
    List<Report> findAllReportsWithoutUser();
}
