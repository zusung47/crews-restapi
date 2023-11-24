package com.mentaljava.mentaljavarestapiproject.table.usercalendar.Controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.dto.UsercalendarDTO;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.service.UserCalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usercalendar")
@Slf4j
public class UserCalendarController {

    private final UserCalendarService userCalendarService;

    public UserCalendarController(UserCalendarService userCalendarService) { this.userCalendarService = userCalendarService; }

    //전체 유저 캘린더 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> selectUserCalendarList(){
        List<UsercalendarDTO> usercalendarList = userCalendarService.findAllUserCalendarList();
        System.out.println("usercalendarList = " + usercalendarList);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"전체 유저 캘린더 조회 성공",usercalendarList));
    }
    @GetMapping ("/list/{userId}")
    public ResponseEntity<ResponseDTO> getUserCalendarsByUserId(@PathVariable User userId) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "해당 유저 리스트 조회 성공", userCalendarService.findUserCalendarsByUserId(userId)));

    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<ResponseDTO> updateUserCalendarByUserId(
            @PathVariable User userId,
            @RequestBody UsercalendarDTO usercalendarDTO) {

        // 서비스 메서드를 호출하여 사용자 캘린더를 업데이트합니다.
        UsercalendarDTO updatedUsercalendarDTO = userCalendarService.updateUserCalendarByUserId(userId, usercalendarDTO);

        // 응답을 반환합니다.
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "유저 캘린더 수정 성공", updatedUsercalendarDTO));
    }

}
