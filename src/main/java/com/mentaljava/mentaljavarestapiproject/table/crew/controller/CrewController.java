package com.mentaljava.mentaljavarestapiproject.table.crew.controller;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.common.PagingDTO;
import com.mentaljava.mentaljavarestapiproject.common.PagingResponseDTO;
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

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    //전체 크루 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> selectCrewList(
            @RequestParam(value = "offset", defaultValue = "1") String offset) {

        int total = crewService.seletTotalCrew();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(crewService.selectCrewListWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 크루 리스트 조회 성공", pagingResponseDTO));
    }

    //크루 카테고리별 조회(운동)
    @GetMapping("/list/exercise")
    public ResponseEntity<ResponseDTO> selectCrewListAboutExercise() {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "카테고리 운동 조회 성공", crewService.selectCrewListAboutExercise()));
    }

    //크루 카테고리별 조회(공부)
    @GetMapping("/list/study")
    public ResponseEntity<ResponseDTO> selectCrewListAboutStudy() {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "카테고리 공부 조회 성공", crewService.selectCrewListAboutStudy()));
    }

    //크루 카테고리별 조회(습관)
    @GetMapping("/list/habit")
    public ResponseEntity<ResponseDTO> selectCrewListAboutHabit() {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "카테고리 습관 조회 성공", crewService.selectCrewListAboutHabit()));
    }

    //크루 카테고리별 조회(기타)
    @GetMapping("/list/etc")
    public ResponseEntity<ResponseDTO> selectCrewListAboutEtc() {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "카테고리 기타 조회 성공", crewService.selectCrewListAboutEtc()));
    }

    //모집상태1 조회
    @GetMapping("/list/recruitmentstatusok")
    public ResponseEntity<ResponseDTO> selectCrewListAboutRecruitmentStatusOk() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "모집 상태1 조회 성공",
                crewService.selectCrewListAboutRecruitmentStatusOk()));
    }

    //모집상태0 조회
    @GetMapping("/list/recruitmentstatusno")
    public ResponseEntity<ResponseDTO> selectCrewListAboutRecruitmentStatusNo() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "모집 상태0 조회 성공",
                crewService.selectCrewListAboutRecruitmentStatusNo()));
    }

    //크루 상세정보 조회
    @GetMapping("/detail/{crewId}")
    public ResponseEntity<ResponseDTO> selectCrewDetail(@PathVariable Integer crewId) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "크루 상세정보 조회 성공", crewService.selectCrew(crewId)));
    }

    //크루 모집글 조회
    @GetMapping("/detail/{crewId}/intro")
    public ResponseEntity<ResponseDTO> selectCrewIntro(@PathVariable Integer crewId) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "크루 소개글 조회 성공", crewService.selectCrewIntro(crewId)));
    }

    //크루 모집글 수정
    @PutMapping(value = "/intro")
    public ResponseEntity<ResponseDTO> updateCrewIntro(@RequestBody CrewDTO crewDTO) {

        log.info("[CrewController] updateCrewIntro crewDTO ===========> " + crewDTO);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "크루 소개글 수정 성공", crewService.updateCrewIntro(crewDTO)));
    }

    //크루 등록
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> insertCrew(@RequestBody CrewDTO crewDTO) {

        log.info("[CrewController] insertCrew crewDTO ===========> " + crewDTO);
        //나중에 시큐리티로 로그인한 사용자 받아와야함

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루 등록 성공", crewService.insertCrew(crewDTO)));
    }
}
