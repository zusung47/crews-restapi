package com.mentaljava.mentaljavarestapiproject.table.user.controller;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.common.PagingDTO;
import com.mentaljava.mentaljavarestapiproject.common.PagingResponseDTO;
import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.DiamondChangeDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.service.UserService;
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

    /**
     * 유저 전체 조회
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> allUserList(){
        List<UserDTO> userList = userService.findAllUserList();
        System.out.println("userList =" + userList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공" ,userList));
    }

    /**
     * 유저 닉네임 변경
     * @param userDTO
     * @return
     */
    @PutMapping("/editnickname")
    public ResponseEntity<ResponseDTO> editUserNickname(@RequestBody UserDTO userDTO) {
        log.info("[UserController] editUserNickname userDTO ===========> " + userDTO);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"유저 닉네임 변경 성공",userService.editUserNickname(userDTO)));
    }

    @PutMapping("/plusdiamond")
    public ResponseEntity<ResponseDTO> plusUserDiamond(@RequestBody UserDTO userDTO) {
        log.info("[UserController] plusUserDiamond userDTO ====> " + userDTO);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"유저 다이아몬드 증가 성공" , userService.plusUserDiamond(userDTO)));
    }
    @PutMapping("/minusdiamond")
    public ResponseEntity<ResponseDTO> minusUserDiamond(@RequestBody UserDTO userDTO) {
        log.info("[UserController] minusUserDiamond userDTO ====> " + userDTO);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"유저 다이아몬드 감소 성공" , userService.minusUserDiamond(userDTO)));
    }

    @PutMapping("/diamond")
    public ResponseEntity<ResponseDTO> submitDiamond(@RequestBody DiamondChangeDTO diamondChangeDTO){

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"다이아몬드 평가 성공", userService.diamondSubmit(diamondChangeDTO)));

    }

    @GetMapping("/userlist")
    public ResponseEntity<ResponseDTO> selectUserListWithPagingTen(
            @RequestParam(value = "offset", defaultValue = "1") String offset) {

        int total = userService.seletTotalUser();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(userService.selectUserListWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 유저 리스트 조회 성공", pagingResponseDTO));
    }

    @PutMapping("update/reportone/{userId}")
    public ResponseEntity<ResponseDTO> updateReportStatusOne(@PathVariable String userId){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "유저 제재 성공", userService.updateReportStatusOne(userId)));
    }

    @PutMapping("update/reportzero/{userId}")
    public ResponseEntity<ResponseDTO> updateReportStatusZero(@PathVariable String userId){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "유저 반성 성공", userService.updateReportStatusZero(userId)));
    }
}
