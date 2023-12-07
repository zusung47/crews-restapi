package com.mentaljava.mentaljavarestapiproject.table.report.service;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.report.dto.ReportDTO;
import com.mentaljava.mentaljavarestapiproject.table.report.entity.Report;
import com.mentaljava.mentaljavarestapiproject.table.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {
    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    public List<ReportDTO> findReportListAboutReportCrew() {

        List<Report> reportList = reportRepository.findAllReportsWithoutUser();

        List<ReportDTO> reportDTOList = reportList.stream()
                .map(report -> modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());

        return reportDTOList;
    }

    public List<ReportDTO> findReportListAboutReportUser() {

        List<Report> reportList = reportRepository.findAllReportsWithoutCrew();

        List<ReportDTO> reportDTOList = reportList.stream()
                .map(report -> modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());

        return reportDTOList;
    }
}
