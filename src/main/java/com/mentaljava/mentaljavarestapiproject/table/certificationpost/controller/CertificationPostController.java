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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
                new ResponseDTO(HttpStatus.OK,"크루의 인증게시판 글 리스트 조회", certificationPostList));
    }

    @GetMapping("/list/{postId}/detail")
    public String postDetail(@PathVariable("postId") Integer postId, Model model) {
        CertificationPost detailOptional = certificationPostService.findPostDetail(postId);
        Optional<CertificationComment> certificationCommentList =
                certificationCommentService.findPostComment(detailOptional, 0);
        if (detailOptional != null) {
            model.addAttribute("postDetail", detailOptional);
            model.addAttribute("postComment", certificationCommentList.get());
            return "certificationpost/postDetail";
        }
        return "redirect:/certificationPost";
    }

}
