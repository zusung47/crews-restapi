package com.mentaljava.mentaljavarestapiproject.table.admin.controller;

import com.mentaljava.mentaljavarestapiproject.table.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;


}
