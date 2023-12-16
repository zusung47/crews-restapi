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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userCalendarId;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CALENDAR_CONTENT")
    private String calendarContent;

    @Column(name = "DELETE_STATUS")
    private Integer deleteStatus;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @Column(name = "TIME")
    private Time time;


    @Column(name = "COLOR")
    private String color;

    public UserCalendar() {}

    public void setUserCalendarId(Integer userCalendarId) {
        this.userCalendarId = userCalendarId;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCalendarContent(String calendarContent) {
        this.calendarContent = calendarContent;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setColor(String color) { this.color = color; }
}
