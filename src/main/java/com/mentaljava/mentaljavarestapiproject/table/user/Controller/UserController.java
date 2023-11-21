package com.mentaljava.mentaljavarestapiproject.table.user.Controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService;}

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> allUserList(){
        List<UserDTO> userList = userService.findAllUserList();
        System.out.println("userList =" + userList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공" ,userList));
    }


}
