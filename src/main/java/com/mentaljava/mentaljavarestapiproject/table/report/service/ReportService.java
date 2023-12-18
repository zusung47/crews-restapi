package com.mentaljava.mentaljavarestapiproject.table.report.service;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.report.dto.ReportDTO;
import com.mentaljava.mentaljavarestapiproject.table.report.entity.Report;
import com.mentaljava.mentaljavarestapiproject.table.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public int seletTotalCrewReport() {

        List<Report> crewReportList = reportRepository.findAllReportsWithoutUser();

        return crewReportList.size();
    }

    public List<ReportDTO> findReportListAboutReportUser() {

        List<Report> reportList = reportRepository.findAllReportsWithoutCrew();

        List<ReportDTO> reportDTOList = reportList.stream()
                .map(report -> modelMapper.map(report, ReportDTO.class)).collect(Collectors.toList());

        return reportDTOList;
    }

    public int seletTotalUserReport() {

        List<Report> userReportList = reportRepository.findAllReportsWithoutCrew();

        return userReportList.size();
    }

    @Transactional
    public String insertReportCrew(ReportDTO reportDTO) {
        log.info("[ReportService] insertReportCrew START =====================");

        int result = 0;

        try {
            Report report = modelMapper.map(reportDTO, Report.class);
            reportRepository.save(report);

            result = 1;
        } catch (Exception e) {
            log.info("[ReportService] insertReportCrew Exception!!!!");
        }

        log.info("[ReportService] insertReportCrew END =====================");

        return (result > 0) ? "신고 입력 성공" : "신고 입력 실패";
    }

    @Transactional
    public String insertReportUser(ReportDTO reportDTO) {
        log.info("[ReportService] insertReportUser START =====================");

        int result = 0;

        try {
            Report report = modelMapper.map(reportDTO, Report.class);
            reportRepository.save(report);

            result = 1;
        } catch (Exception e) {
            log.info("[ReportService] insertReportUser Exception!!!!");
        }

        log.info("[ReportService] insertReportUser END =====================");

        return (result > 0) ? "신고 입력 성공" : "신고 입력 실패";
    }

    public List<ReportDTO>  selectCrewReportListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("reportId").descending());

        Page<Report> result = reportRepository.findAllReportsWithoutUser(paging);
        List<ReportDTO> reportDTOList = result.stream()
                .map(report -> modelMapper.map(report, ReportDTO.class))
                .collect(Collectors.toList());

        return reportDTOList;

    }

    public List<ReportDTO> selectUserReportListWithPaging(Criteria cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("reportId").descending());

        Page<Report> result = reportRepository.findAllReportsWithoutCrew(paging);
        List<ReportDTO> reportDTOList = result.stream()
                .map(report -> modelMapper.map(report, ReportDTO.class))
                .collect(Collectors.toList());

        return reportDTOList;

    }
}
