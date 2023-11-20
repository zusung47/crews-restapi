package com.mentaljava.mentaljavarestapiproject.table.certificationcomment.controller;

import com.mentaljava.mentaljavarestapiproject.table.certificationcomment.service.CertificationCommentService;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.service.CertificationPostService;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.user.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CertificationCommentController {

    private final CertificationCommentService certificationCommentService;
    private final UserService userService;
    private final CertificationPostService certificationPostService;

    @PostMapping("/comment/regist")
    public String commentInput(@RequestParam("postComment")String postComment,
                               @RequestParam("postId")Integer postId){
        User user = userService.findOne("seyoung2");
        CertificationPost postDetail = certificationPostService.findPostDetail(postId);

        certificationCommentService.newComment(postComment,user,postDetail);
        return "redirect:/certificationPost";
    }
}
