package com.mentaljava.mentaljavarestapiproject.table.admin.controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.admin.service.AdminService;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.service.CrewService;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@Slf4j
public class AdminController {

    private final UserService userService;
    private final CrewService crewService;

    public AdminController(UserService userService, CrewService crewService) {
        this.userService = userService;
        this.crewService = crewService;
    }

    // 크루원 조회
    @GetMapping("/crewMemberList")
    public ResponseEntity<ResponseDTO> crewMemberList(){
        List<UserDTO> crewMemberList = userService.findAllUserList();
        System.out.println("userList =" + crewMemberList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공" ,crewMemberList));
    }

    // 크루 조회
    @GetMapping("/crewList")
    public ResponseEntity<ResponseDTO> CrewList(){
        List<CrewDTO> crewList = crewService.findAllCrewList();
        System.out.println("crewList = " + crewList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 크루 리스트 조회 성공", crewList));
    }

    // 크루원 삭제
    @GetMapping("/list/{userId}/delete")
    public ResponseEntity<ResponseDTO> deleteCrewMember(@PathVariable("userId") String userId){
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"공지사항 삭제 성공",userService.deleteUser(userId)));
    }

    // 크루 삭제
    @GetMapping("/list/{crewId}/delete")
    public ResponseEntity<ResponseDTO> deleteCrew(@PathVariable("crewId") Integer crewId){
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"공지사항 삭제 성공",crewService.deleteCrew(crewId)));
    }
}
