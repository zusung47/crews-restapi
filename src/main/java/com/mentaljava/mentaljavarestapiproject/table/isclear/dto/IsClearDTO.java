package com.mentaljava.mentaljavarestapiproject.table.isclear.dto;

import com.mentaljava.mentaljavarestapiproject.table.usercalendar.dto.UsercalendarDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class IsClearDTO {

    private Integer clearId;

    private String toDoList;

    private String checkBox;

    private UsercalendarDTO userCalendarId;
}
