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
// 시작일
    private Date endDate;
//종료일
    private Integer repeatNum;
//    일정이 이벤트에 찍히는 주기
}
