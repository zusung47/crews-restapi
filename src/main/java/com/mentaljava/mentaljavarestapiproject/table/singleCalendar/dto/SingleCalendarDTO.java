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

    private String groupId;

    private String title;


    //첫번째 날짜(for문)
    private Date firstDate;

    //마지막 날짜(for문)
    private Date lastDate;

    //반복주트
    private Integer repeatNum;

}
