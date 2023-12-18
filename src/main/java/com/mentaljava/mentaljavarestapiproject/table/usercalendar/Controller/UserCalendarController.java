package com.mentaljava.mentaljavarestapiproject.table.usercalendar.Controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.notice.dto.NoticeDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.dto.UsercalendarDTO;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.entity.UserCalendar;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.service.UserCalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/v1/usercalendar")
@Slf4j
public class UserCalendarController {

    private final UserCalendarService userCalendarService;

    public UserCalendarController(UserCalendarService userCalendarService) {
        this.userCalendarService = userCalendarService;
    }

    //전체 유저 캘린더 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> selectUserCalendarList() {
        List<UsercalendarDTO> usercalendarList = userCalendarService.findAllUserCalendarList();
        System.out.println("usercalendarList = " + usercalendarList);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "전체 유저 캘린더 조회 성공", usercalendarList));
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<ResponseDTO> getUserCalendarsByUserId(@PathVariable User userId) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "해당 유저 리스트 조회 성공",
                userCalendarService.findUserCalendarsByUserId(userId)));

    }

    @PutMapping("/update/{userId}/{userCalendarId}")
    public ResponseEntity<ResponseDTO> updateUserCalendarByUserId(
            @PathVariable User userId,
            @PathVariable Integer userCalendarId,
            @RequestBody UsercalendarDTO usercalendarDTO) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(usercalendarDTO.getEndDate());
        cal.add(Calendar.DATE, 1);
        usercalendarDTO.setEndDate(cal.getTime());
        List<UsercalendarDTO> updatedUsercalendars = userCalendarService.updateUserCalendarByUserId(userId,
                userCalendarId, usercalendarDTO);

        log.info("[UserController] updateUserCalendarByUserId usercalendarDTO ===========> " + updatedUsercalendars);

        // 응답을 반환합니다.
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "유저 캘린더 수정 성공", updatedUsercalendars));
    }


    @GetMapping("list/StartDate/{startDate}")
    public ResponseEntity<ResponseDTO> findUserCalendarsByStartDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate) {
        List<UsercalendarDTO> userCalendars = userCalendarService.findUserCalendarsByStartDate(startDate);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "유저 캘린더 조회 성공", userCalendars));
    }

    @GetMapping("list/EndDate/{endDate}")
    public ResponseEntity<ResponseDTO> findUserCalendarsByEndDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<UsercalendarDTO> userCalendars = userCalendarService.findUserCalendarsByEndDate(endDate);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "유저 캘린더 조회 성공", userCalendars));
    }

    @PostMapping("/regist/{userId}")
    public ResponseEntity<ResponseDTO> newCalendar(@PathVariable User userId,
                                                   @RequestBody UsercalendarDTO usercalendarDTO) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(usercalendarDTO.getEndDate());
        cal.add(Calendar.DATE, 1);
        usercalendarDTO.setEndDate(cal.getTime());
        log.info("[UserController] insertusercalnedar usercalendarDTO ===========> " + usercalendarDTO);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "일정 등록 성공",
                        userCalendarService.insertUsercalendar(userId, usercalendarDTO)));

    }

    @DeleteMapping("/delete/{userId}/{userCalendarId}")
    public ResponseEntity<ResponseDTO> deleteCalendar(
            @PathVariable User userId,
            @PathVariable Integer userCalendarId
    ) {
        // 해당 userId와 userCalendarId를 사용하여 이벤트를 삭제합니다.
        userCalendarService.deleteUserCalendar(userId, userCalendarId);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "이벤트 삭제 성공", null));
    }

}
