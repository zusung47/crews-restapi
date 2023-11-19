package com.mentaljava.mentaljavarestapiproject.table.certificationpost.controller;

import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.certificationpost.service.CertificationPostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/certificationPost")
public class CertificationPostController {

    private final CertificationPostService certificationPostService;

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


}
