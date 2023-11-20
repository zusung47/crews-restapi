package com.mentaljava.mentaljavarestapiproject.table.crewcalendar.entity;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CREW_CALENDAR")
@AllArgsConstructor
@Getter
@ToString
public class CrewCalendar {

    @Id
    @Column(name = "CREW_CALENDAR_ID")
    @GeneratedValue
    private int crewCalendarId;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "DELETE_STATUS")
    private int deleteStatus;

    @ManyToOne
    @JoinColumn(name = "CREW_ID")
    private Crew crewId;

    public CrewCalendar() {}

    public void setCrewCalendarId(int crewCalendarId) {
        this.crewCalendarId = crewCalendarId;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setCrewId(Crew crewId) {
        this.crewId = crewId;
    }
}
