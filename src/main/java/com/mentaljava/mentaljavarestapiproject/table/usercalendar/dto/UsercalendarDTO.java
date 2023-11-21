package com.mentaljava.mentaljavarestapiproject.table.usercalendar.dto;

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

    private Date startDate;

    private Date endDate;

    private String title;

    private String calendarContent;

    private Integer deleteStatus;

    private UserDTO userId;

    private Time time;
}
