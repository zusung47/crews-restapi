package com.mentaljava.mentaljavarestapiproject.table.singleCalendar.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RepeatDTO {

    private Date startDate;

    private Date endDate;

    private Integer repeatNum;
}
