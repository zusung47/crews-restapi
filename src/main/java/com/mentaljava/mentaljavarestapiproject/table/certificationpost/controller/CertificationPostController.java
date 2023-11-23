package com.mentaljava.mentaljavarestapiproject.table.certificationpost.controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.service.CertificationCommentService;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto.CertificationPostDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.service.CertificationPostService;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.service.CrewService;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/certificationPost")
public class CertificationPostController {

    private final CertificationPostService certificationPostService;
    private final CertificationCommentService certificationCommentService;

    @GetMapping("/{crewId}/list")
    public ResponseEntity<ResponseDTO> certificationPostList(@PathVariable Integer crewId) {
        List<CertificationPostDTO> certificationPostList = certificationPostService.findOnePost(crewId);

        log.info("[CertificationPost] CertificationDTO ===========> " + certificationPostList);
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "크루의 인증게시판 글 리스트 조회", certificationPostList));
    }

    @PutMapping("/{crewIds}/regist")
    public ResponseEntity<ResponseDTO> registPost(@PathVariable Integer crewIds,
                                                  @RequestBody CertificationPostDTO certificationPostDTO) {

        log.info("[CertificationPost] CertificationDTO ===========> " + certificationPostDTO);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK, "크루의 인증게시판 글 만들",
                        certificationPostService.registComment(crewIds, certificationPostDTO)));

    }

}
