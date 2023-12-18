package com.mentaljava.mentaljavarestapiproject.table.singleCalendar.dto;

import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SingleCalendarDTO {

    private Integer singleCalendarId;

    private UserDTO userId;

    private Date startDate;

    private Integer groupId;

    private String title;

}
