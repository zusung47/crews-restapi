package com.mentaljava.mentaljavarestapiproject.table.report.controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.report.dto.ReportDTO;
import com.mentaljava.mentaljavarestapiproject.table.report.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@Slf4j
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/crew")
    public ResponseEntity<ResponseDTO> insertReportCrew(@RequestBody ReportDTO reportDTO){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루 신고 성공", reportService.insertReportCrew(reportDTO)));
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseDTO> insertReportUser(@RequestBody ReportDTO reportDTO){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "유저 신고 성공", reportService.insertReportUser(reportDTO)));
    }
}
