package com.mentaljava.mentaljavarestapiproject.table.singleCalendar.controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.dto.RepeatDTO;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.dto.SingleCalendarDTO;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.service.SingleCalendarService;
import java.util.Calendar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/singleCalendar")
@Slf4j
public class SingleCalendarController {

    private final SingleCalendarService singleCalendarService;

    @GetMapping("/{userId}/list")
    public ResponseEntity<ResponseDTO> userSingleCalendarSelect(
            @PathVariable String userId){
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "유저의 싱글 캘린더 조회", singleCalendarService.singleCalendarSelect(userId)));
    }

//    @PostMapping("/regist/{userId}")
//    public ResponseEntity<ResponseDTO> registSigleCalendar(
//            @PathVariable String userId,
//            @RequestBody SingleCalendarDTO singleCalendarDTO){
//
//        Calendar cal = Calendar.getInstance();
//
//        cal.setTime(singleCalendarDTO.getStartDate());
//        cal.add(Calendar.DATE,1);
//        return ResponseEntity.ok().body(
//                new ResponseDTO(HttpStatus.OK, "싱글캘린더 일정 등록성공",
//                        singleCalendarService.insertSingleCalendar(userId,singleCalendarDTO))
//        );
//
//    }

    @PutMapping("/add/{userId}")
    public ResponseEntity<ResponseDTO> addSingleCalendar(
            @PathVariable String userId,
            @RequestBody SingleCalendarDTO singleCalendarDTO
            ){
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "싱글캘린더 일정 등록성공",
                        singleCalendarService.insertSingleCalendar(userId,singleCalendarDTO)));
    }



}
