package com.mentaljava.mentaljavarestapiproject.table.certificationpost.controller;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.entity.CertificationComment;
import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.service.CertificationCommentService;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto.CertificationPostDTO;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.service.CertificationPostService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/certificationPost")
public class CertificationPostController {

    private final CertificationPostService certificationPostService;
    private final CertificationCommentService certificationCommentService;

    @GetMapping("")
    public String certificationPostForm() {
        return "certificationpost/certificationForm";
    }

    @GetMapping("/list")
    public String certificationPostList(Model model) {
        List<CertificationPost> lists = certificationPostService.findCertificationPost();
        model.addAttribute("postList", lists);
        return "certificationpost/certificationPostList";
    }

    // 상세조회 기능 작동오류
    @GetMapping("/list/{postId}/detail")
    public String postDetail(@PathVariable("postId") Integer postId, Model model) {
        Optional<CertificationPost> detailOptional = certificationPostService.findPostDetail(postId);
        List<CertificationComment> certificationCommentList = certificationCommentService.findPostComment(postId,0);
        if (detailOptional.isPresent()) {
            model.addAttribute("postDetail", detailOptional.get());
            model.addAttribute("postComment",certificationCommentList);
            return "certificationpost/postDetail";
        }
        return "redirect:/certificationPost";
    }

}
