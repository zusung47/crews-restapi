package com.mentaljava.mentaljavarestapiproject.table.crewlist.controller;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.common.PagingDTO;
import com.mentaljava.mentaljavarestapiproject.common.PagingResponseDTO;
import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.dto.CrewListDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.service.CrewListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crewlist")
@Slf4j
public class CrewListController {

    private CrewListService crewListService;

    public CrewListController(CrewListService crewListService) {
        this.crewListService = crewListService;
    }

    //크루신청현황조회
    @GetMapping("/select/detail/{crewId}")
    public ResponseEntity<ResponseDTO> selectCrewListByCrewId(@PathVariable Integer crewId) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "크루신청현황조회성공", crewListService.selectCrewListByCrewId(crewId)));
    }

    //크루신청대기상태조회 (대기상태는 approvalStatus = 0)
    @GetMapping("/applylist/{crewId}")
    public ResponseEntity<ResponseDTO> selectCrewListByCrewIdAndWaitStatus(@PathVariable Integer crewId) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루별대기상태조회성공",
                crewListService.selectCrewListByCrewIdAndWaitStatus(crewId)));
    }

    //크루원 받아주기 (허락상태는 approvalStatus = 1)
    @PutMapping(value = "/agreestatus")
    public ResponseEntity<ResponseDTO> updateStatusAgree(@RequestBody CrewListDTO crewListDTO) {

        log.info("[CrewListController] updateStatusAgree CrewListDto ============> " + crewListDTO);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "신청상태변경완료", crewListService.updateStatusAgree(crewListDTO)));
    }

    //크루원 거절하기 (거절상태는 approvalStatus = 2)
    @PutMapping(value = "/rejectionstatus")
    public ResponseEntity<ResponseDTO> updateStatusRejection(@RequestBody CrewListDTO crewListDTO) {

        log.info("[CrewListController] updateStatusRejection CrewListDto ============> " + crewListDTO);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "신청상태변경완료", crewListService.updateStatusRejection(crewListDTO)));
    }

    @GetMapping("/{userId}/crew")
    public ResponseEntity<ResponseDTO> getCrewListByUserId(
            @PathVariable String userId,
            @RequestParam(value = "offset", defaultValue = "1") String offset
            ) {

        int total = crewListService.seletTotalCrewList("userId");

        Criteria cri = new Criteria(Integer.valueOf(offset),10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(crewListService.selectCrewListWithPaging(userId,cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri,total));


        log.info("가입된 크루" + userId);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "회원의 크루조회 완료", pagingResponseDTO));
    }

    @GetMapping("/{userId}/endCrew")
    public ResponseEntity<ResponseDTO> getEndCrew(
            @PathVariable String userId,
            @RequestParam(value = "offset", defaultValue = "1") String offset
    ){
        int total = crewListService.seletTotalCrewList("userId");
        Criteria cri = new Criteria(Integer.valueOf(offset), 5);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(crewListService.selectEndCrewListWithPaging(userId,cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri,total));

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"종료된 크루 조회 완료",pagingResponseDTO));
    }

    //크루 신청하기
    @PostMapping("/apply/{crewId}")
    public ResponseEntity<ResponseDTO> insertCrewListApply(@PathVariable Integer crewId,
                                                           @RequestBody CrewListDTO crewListDTO) {

        //나중에 시큐리티로 사용자 받아와야함

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루신청완료",
                crewListService.insertCrewListApply(crewId, crewListDTO)));
    }
}
