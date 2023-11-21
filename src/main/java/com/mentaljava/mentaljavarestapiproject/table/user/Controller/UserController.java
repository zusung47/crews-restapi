package com.mentaljava.mentaljavarestapiproject.table.user.Controller;

import com.mentaljava.mentaljavarestapiproject.table.user.service.UserService;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.entity.UserCalendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public String userNav() {
        return "user/userForm";
    }

    @GetMapping("/update")
    public String updateFrom() {
        return "user/updateNickname";
    }

    @PostMapping("/update")
    public String updateNickname(@RequestParam("userId") String userId,
                                 @RequestParam("nickname") String nickname) {
        userService.updateNickname(userId, nickname);
        return "redirect:/user";
    }
}
