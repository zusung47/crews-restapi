package com.mentaljava.mentaljavarestapiproject.table.crew.controller;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.common.PagingDTO;
import com.mentaljava.mentaljavarestapiproject.common.PagingResponseDTO;
import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.service.CrewService;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
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
    public ResponseEntity<ResponseDTO> selectCrewListAboutExerciseWithPaging(
            @RequestParam(value = "offset", defaultValue = "1") String offset) {

        log.info("[CrewController] selectCrewListAboutExerciseWithPaging start =============");
        log.info("[CrewController] selectCrewListAboutExerciseWithPaging offset : {} ", offset);

        int total = crewService.selectTotalCrewListAboutExercise();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(crewService.selectCrewListAboutExerciseWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "카테고리 운동 조회 성공", pagingResponseDTO));
    }

    //크루 카테고리별 조회(공부)
    @GetMapping("/list/study")
    public ResponseEntity<ResponseDTO> selectCrewListAboutStudyWithPaging(
            @RequestParam(value = "offset", defaultValue = "1") String offset) {

        log.info("[CrewController] selectCrewListAboutStudyWithPaging start =============");
        log.info("[CrewController] selectCrewListAboutStudyWithPaging offset : {} ", offset);

        int total = crewService.selectTotalCrewListAboutStudy();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(crewService.selectCrewListAboutStudyWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "카테고리 공부 조회 성공", pagingResponseDTO));
    }

    //크루 카테고리별 조회(습관)
    @GetMapping("/list/habit")
    public ResponseEntity<ResponseDTO> selectCrewListAboutHabitWithPaging(
            @RequestParam(value = "offset", defaultValue = "1") String offset) {

        log.info("[CrewController] selectCrewListAboutHabitWithPaging start =============");
        log.info("[CrewController] selectCrewListAboutHabitWithPaging offset : {} ", offset);

        int total = crewService.selectTotalCrewListAboutHabit();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(crewService.selectCrewListAboutHabitWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "카테고리 습관 조회 성공", pagingResponseDTO));
    }

    //크루 카테고리별 조회(기타)
    @GetMapping("/list/etc")
    public ResponseEntity<ResponseDTO> selectCrewListAboutEtcWithPaging(
            @RequestParam(value = "offset", defaultValue = "1") String offset) {

        log.info("[CrewController] selectCrewListAboutEtcWithPaging start =============");
        log.info("[CrewController] selectCrewListAboutEtcWithPaging offset : {} ", offset);

        int total = crewService.selectTotalCrewListAboutEtc();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(crewService.selectCrewListAboutEtcWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "카테고리 기타 조회 성공", pagingResponseDTO));
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

    //크루 소개글 조회
    @GetMapping("/detail/{crewId}/intro")
    public ResponseEntity<ResponseDTO> selectCrewIntro(@PathVariable Integer crewId) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "크루 소개글 조회 성공", crewService.selectCrewIntro(crewId)));
    }

    //크루 소개글 수정
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

    //크루이름 검색해서 조회
    @GetMapping("/list/search")
    public ResponseEntity<ResponseDTO> selectSearchCrewList(
            @RequestParam(name = "s", defaultValue = "all") String search){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "검색 조회 성공", crewService.selectSearchCrewList(search)));
    }

    //캡틴을 통해 크루 정보 조회 추후 필요한 데이터만 가져오도록 수정(내가 쓴 글 조회)
    @GetMapping("/list/{captain}/mypost")
    public ResponseEntity<ResponseDTO> getCrewByCaptain(@PathVariable User captain) {
        List<CrewDTO> Crews = crewService.getCrewByCaptain(captain);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"작성글 조회 성공",Crews));
    }
}
