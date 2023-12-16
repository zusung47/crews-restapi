package com.mentaljava.mentaljavarestapiproject.table.usercalendar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsercalendarDTO {

    private Integer userCalendarId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9") //날짜 형식 포맷 수정
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private Date endDate;

    private String title;

    private String calendarContent;

    private Integer deleteStatus;

    private UserDTO userId;

    private Time time;

    private String color;

    private String borderColor;

    private String textColor;
}
