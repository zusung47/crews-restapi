package com.mentaljava.mentaljavarestapiproject.table.crewcheck.controller;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.common.PagingDTO;
import com.mentaljava.mentaljavarestapiproject.common.PagingResponseDTO;
import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.dto.CrewCheckDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.service.CrewCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crewcheck")
@Slf4j
public class CrewCheckController {

    private CrewCheckService crewCheckService;

    public CrewCheckController(CrewCheckService crewCheckService) {
        this.crewCheckService = crewCheckService;
    }

    //크루소속원별활동조회
    @GetMapping("list/{crewId}/{userId}")
    public ResponseEntity<ResponseDTO> selectCrewCheckListByUserId(
            @PathVariable Integer crewId, @PathVariable String userId,
            @RequestParam(value = "offset", defaultValue = "1") String offset
    ){

        int total = crewCheckService.selectCrewCheckListByUserId(userId, crewId);

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(crewCheckService.selectCrewCheckListByUserIdWithPaging(userId, crewId, cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루소속원별활동조회성공", pagingResponseDTO));
    }

    @PutMapping("/list/update")
    public ResponseEntity<ResponseDTO> updateCrewCheck(@RequestBody CrewCheckDTO crewCheckDTO) {

        log.info("[CrewCheckController] updateCrewCheck crewCheckDTO ==========> " + crewCheckDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루체크수정성공", crewCheckService.updateCrewCheck(crewCheckDTO)));
    }
}
