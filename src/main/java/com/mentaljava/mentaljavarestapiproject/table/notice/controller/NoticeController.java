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
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class NoticeController {

    private final NoticeService noticeService;
    private final AdminService adminService;

    @GetMapping("")
    public String noticeNav() {
        return "notice/noticeForm";
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> noticeList() {
        List<NoticeDTO> noticeList = noticeService.findNotice();
        System.out.println("noticeList = " + noticeList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", noticeList));
    }

    @GetMapping("/list/{noticeId}/edit")
    public ResponseEntity<ResponseDTO> updateNotice(@PathVariable("noticeId") Integer noticeId) {
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"공지사항 상세정보 조회 성공",noticeService.findOne(noticeId)));
    }

    @PutMapping("/list/{noticeId}/update")
    public UpdateNotice updateNotice(@PathVariable("noticeId") Integer noticeId, @RequestBody UpdateNotice updateNotice) {
        noticeService.update(noticeId,updateNotice.getNoticeTitle(),updateNotice.getNoticeContent());
        Notice notice = noticeService.updateOneNotice(noticeId);
        return new UpdateNotice(notice.getNoticeId(),notice.getNoticeTitle(),notice.getNoticeContent());
    }

    @Data
    @AllArgsConstructor
    static class UpdateNotice{
        private Integer noticeId;
        private String noticeTitle;
        private String noticeContent;
    }

    @GetMapping("/list/{noticeId}/delete")
    public String deleteNotice(@PathVariable("noticeId") Integer noticeId){
        noticeService.deleteNotice(noticeId);
        return "redirect:/notice";
    }


    @PutMapping("/regist")
    public ResponseEntity<ResponseDTO> newNotice(@RequestBody NoticeDTO noticeDTO){

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"공지사항 등록 성공",noticeService.insertNotice(noticeDTO)));

    }

}
