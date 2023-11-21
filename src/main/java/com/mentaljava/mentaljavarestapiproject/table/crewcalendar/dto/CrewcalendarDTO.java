package com.mentaljava.mentaljavarestapiproject.table.crewcalendar.dto;

import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CrewcalendarDTO {

    private Integer crewCalendarId;

    private Date startDate;

    private Date endDate;

    private String title;

    private String content;

    private Integer deleteStatus;

    private CrewDTO crewId;
}
