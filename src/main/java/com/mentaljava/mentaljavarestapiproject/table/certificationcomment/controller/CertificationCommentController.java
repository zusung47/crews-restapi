package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.controller;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.common.PagingDTO;
import com.mentaljava.mentaljavarestapiproject.common.PagingResponseDTO;
import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.dto.CertificationCommentDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.service.CertificationCommentService;


import com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto.CertificationPostDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/certificationcomment")
@Slf4j
@RequiredArgsConstructor
public class CertificationCommentController {

    private final CertificationCommentService certificationCommentService;

    @GetMapping("/{postId}/list/detail")
    public ResponseEntity<ResponseDTO> postDetail(
            @PathVariable Integer postId,
            @RequestParam(value = "offset", defaultValue = "1") String offset
        ) {
        int total = certificationCommentService.selectTotalComment(postId);

        Criteria cri = new Criteria(Integer.valueOf(offset),10);
        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(certificationCommentService.selectComment(postId,cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "댓글 게시판 조회", pagingResponseDTO));
    }

//    @PostMapping("/regist/comment")
//    public ResponseEntity<ResponseDTO> registComment(@ModelAttribute CertificationCommentDTO certificationCommentDTO,
//                                                     MultipartFile commentImage) {
//        log.info("[registComment] DTO ===========> " + certificationCommentDTO);
//        return ResponseEntity.ok().body(
//                new ResponseDTO(HttpStatus.OK, "댓글 등록 성공", certificationCommentService.insertComment(certificationCommentDTO,commentImage)));
//
//    }
    @PostMapping("/regist/comment")
    public ResponseEntity<ResponseDTO> registComment(@RequestParam String commentContent,
                                                     @RequestParam String userId,
                                                     @RequestParam String postId,
                                                     @RequestParam("commentImage") MultipartFile commentImage) {
        CertificationCommentDTO certificationCommentDTO = new CertificationCommentDTO();

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        certificationCommentDTO.setUserId(userDTO);

        CertificationPostDTO certificationPostDTO = new CertificationPostDTO();
        certificationPostDTO.setPostId((int) Long.parseLong(postId));
        certificationCommentDTO.setPostId(certificationPostDTO);

        certificationCommentDTO.setCommentContent(commentContent);

        log.info("[registComment] DTO ===========> " + certificationCommentDTO);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "댓글 등록 성공",
                        certificationCommentService.insertComment(certificationCommentDTO, commentImage)));
    }

}
