package com.mentaljava.mentaljavarestapiproject.table.notice.controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.admin.service.AdminService;
import com.mentaljava.mentaljavarestapiproject.table.notice.dto.NoticeDTO;
import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import com.mentaljava.mentaljavarestapiproject.table.notice.service.NoticeService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 리스트 출력 api
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> noticeList() {
        List<NoticeDTO> noticeList = noticeService.findNotice();
        System.out.println("noticeList = " + noticeList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", noticeList));
    }

    // 공지사항 상세정보 조회
    @GetMapping("/list/{noticeId}/edit")
    public ResponseEntity<ResponseDTO> updateNotice(@PathVariable("noticeId") Integer noticeId) {
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"공지사항 상세정보 조회 성공",noticeService.findOne(noticeId)));
    }

    // 공지사항 수정
    @PutMapping("/list/update")
    public ResponseEntity<ResponseDTO> updateNotice(@RequestBody NoticeDTO noticeDTO) {
        log.info("[NoticeController] updateNotice noticeDTO ===========> " + noticeDTO);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"공지사항 수정 성공",noticeService.updateNotice(noticeDTO)));
    }

    //공지사항 등록
    @PutMapping("/regist")
    public ResponseEntity<ResponseDTO> newNotice(@RequestBody NoticeDTO noticeDTO){

        log.info("[NoticeController] insertnotice noticeDTO ===========> " + noticeDTO);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"공지사항 등록 성공",noticeService.insertNotice(noticeDTO)));

    }

    //공지사항 삭제
    @DeleteMapping("/list/{noticeId}/edit")
    public ResponseEntity<ResponseDTO> deleteNotice(@PathVariable("noticeId") Integer noticeId){
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"공지사항 삭제 성공",noticeService.deleteNotice(noticeId)));
    }


}
