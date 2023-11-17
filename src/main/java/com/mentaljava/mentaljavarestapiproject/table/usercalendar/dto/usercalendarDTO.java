package com.mentaljava.mentaljavarestapiproject.table.usercalendar.dto;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class usercalendarDTO {

    private int userCalendarId;

    private Date startDate;

    private Date endDate;

    private String title;

    private String calendarContent;

    private int deleteStatus;

    private User userId;

    private Time time;
}
