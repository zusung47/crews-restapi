package com.mentaljava.mentaljavarestapiproject.table.crew.controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.service.CrewService;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crew")
@Slf4j
public class CrewController {

    private final CrewService crewService;

    public CrewController(CrewService crewService) { this.crewService = crewService; }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> selectCrewList(){
        List<CrewDTO> crewList = crewService.findAllCrewList();
        System.out.println("crewList = " + crewList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", crewList));
    }
}
