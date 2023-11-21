package com.mentaljava.mentaljavarestapiproject.table.crew.controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.service.CrewService;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crew")
@Slf4j
public class CrewController {

    private final CrewService crewService;

    public CrewController(CrewService crewService) { this.crewService = crewService; }

    //전체 크루 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> selectCrewList(){
        List<CrewDTO> crewList = crewService.findAllCrewList();
        System.out.println("crewList = " + crewList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 크루 리스트 조회 성공", crewList));
    }

    //모집상태1 조회
    @GetMapping("/list/recruitmentstatusok")
    public ResponseEntity<ResponseDTO> selectCrewListAboutRecruitmentStatusOk(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "모집 상태1 조회 성공", crewService.selectCrewListAboutRecruitmentStatusOk()));
    }

    //모집상태0 조회
    @GetMapping("/list/recruitmentstatusno")
    public ResponseEntity<ResponseDTO> selectCrewListAboutRecruitmentStatusNo(){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "모집 상태0 조회 성공", crewService.selectCrewListAboutRecruitmentStatusNo()));
    }

    //크루 상세정보 조회
    @GetMapping("/detail/{crewId}")
    public ResponseEntity<ResponseDTO> selectCrewDetail(@PathVariable Integer crewId){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루 상세정보 조회 성공", crewService.selectCrew(crewId)));
    }

    //크루 모집글 조회
    @GetMapping("/detail/{crewId}/intro")
    public ResponseEntity<ResponseDTO> selectCrewIntro(@PathVariable Integer crewId){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루 소개글 조회 성공", crewService.selectCrewIntro(crewId)));
    }

    //크루 모집글 수정
    @PutMapping(value = "/intro")
    public ResponseEntity<ResponseDTO> updateCrewIntro(@RequestBody CrewDTO crewDTO){

        log.info("[CrewController] updateCrewIntro crewDTO ===========> " + crewDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루 소개글 수정 성공", crewService.updateCrewIntro(crewDTO)));
    }
}
