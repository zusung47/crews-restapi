package com.mentaljava.mentaljavarestapiproject.table.notice.controller;

import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.admin.service.AdminService;
import com.mentaljava.mentaljavarestapiproject.table.notice.dto.NoticeDTO;
import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import com.mentaljava.mentaljavarestapiproject.table.notice.service.NoticeService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final AdminService adminService;

    @GetMapping("")
    public String noticeNav() {
        return "notice/noticeForm";
    }

    @GetMapping("/list")
    public String noticeList(Model model) {
        List<Notice> noticeList = noticeService.findNotice();
        model.addAttribute("notices", noticeList);
        return "notice/noticeList";
    }

    @GetMapping("/list/{noticeId}/edit")
    public String updateNotice(@PathVariable("noticeId") Integer noticeId, Model model) {
        Notice one = noticeService.findOne(noticeId);

        if (one != null) {
            NoticeDTO form = new NoticeDTO();
            form.setNoticeId(one.getNoticeId());
            form.setNoticeTitle(one.getNoticeTitle());
            form.setNoticeContent(one.getNoticeContent());
            form.setAdminId(one.getAdminId());
            form.setNoticeDate(one.getNoticeDate());

            model.addAttribute("form", form);
            return "notice/update";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/list/{noticeId}/edit")
    public String registNotice(@PathVariable("noticeId") Integer noticeId, @ModelAttribute("form") NoticeDTO form) {
        noticeService.updateNotice(noticeId, form.getNoticeTitle(), form.getNoticeContent());
        return "redirect:/notice";
    }

    @GetMapping("/list/{noticeId}/delete")
    public String deleteNotice(@PathVariable("noticeId") Integer noticeId){
        noticeService.deleteNotice(noticeId);
        return "redirect:/notice";
    }

    @GetMapping("/regist")
    public String noticeAdd(Model model) {
        List<Admin> adminList = adminService.findAdmin();
        model.addAttribute("admins", adminList);
        model.addAttribute("form", new NoticeDTO());
        return "notice/noticeRegistForm";
    }


    // 이부분이 작동되지 않음.
    @PostMapping("/regist")
    public String Create(NoticeDTO form) {
        Notice notice = new Notice();
        notice.setNoticeTitle(form.getNoticeTitle());
        notice.setNoticeContent(form.getNoticeContent());
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);
        notice.setNoticeDate(sqlDate);
        notice.setAdminId(adminService.findOne(form.getAdminId()));
        noticeService.saveNotice(notice);
        return "redirect:/notice";
    }

}
