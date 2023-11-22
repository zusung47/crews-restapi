package com.mentaljava.mentaljavarestapiproject.table.report.service;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.report.dto.ReportDTO;
import com.mentaljava.mentaljavarestapiproject.table.report.repository.ReportRepository;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ReportService {
    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    public ReportService(ReportRepository reportRepository, ModelMapper modelMapper){
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
    }

    public List<ReportDTO> findAllReportList() {

        List<Admin> reportList = reportRepository.findAll();
        List<ReportDTO> reportDTOList = reportList.stream().map(crew -> modelMapper.map(reportList, ReportDTO.class)).collect(Collectors.toList());
        return reportDTOList;
    }
}
