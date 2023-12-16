package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.controller;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.common.PagingDTO;
import com.mentaljava.mentaljavarestapiproject.common.PagingResponseDTO;
import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.dto.CertificationCommentDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.service.CertificationCommentService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @PutMapping("/{postId}/list/comment")
    public ResponseEntity<ResponseDTO> registComment(@PathVariable Integer postId,
                                                     @RequestBody CertificationCommentDTO certificationCommentDTO,
                                                     MultipartFile commentImage) {
        log.info("[registComment] DTO ===========> " + certificationCommentDTO);
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "댓글 게시판 조회", certificationCommentService.addComment(certificationCommentDTO,commentImage)));

    }

}
