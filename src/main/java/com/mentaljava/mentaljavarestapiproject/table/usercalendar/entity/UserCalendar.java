package com.mentaljava.mentaljavarestapiproject.table.usercalendar.entity;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "USER_CALENDAR")
@AllArgsConstructor
@Getter
@ToString
public class UserCalendar {

    @Id
    @Column(name = "USER_CALENDAR_ID")
    private int userCalendarId;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CALENDAL_CONTENT")
    private String calendarContent;

    @Column(name = "DELETE_STATUS")
    private int deleteStatus;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @Column(name = "TIME")
    private Time time;
}
