package com.mentaljava.mentaljavarestapiproject.table.isclear.entity;

import com.mentaljava.mentaljavarestapiproject.table.usercalendar.dto.UsercalendarDTO;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.entity.UserCalendar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "IS_CLEAR")
@AllArgsConstructor
@Getter
@ToString
public class IsClear {

    @Id
    @Column(name = "CELAR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clearId;

    @Column(name = "TODO_LIST")
    private String toDoList;

    @Column(name = "CHECK_BOX")
    private String checkBox;

    @ManyToOne
    @JoinColumn(name = "USER_CALENDAR_ID")
    private UserCalendar userCalendarId;

    public IsClear() {}

    public void setClearId(Integer clearId) {this.clearId = clearId;}

    public void setToDoList(String toDoList) {this.toDoList = toDoList;}

    public void setCheckBox(String checkBox) {this.checkBox = checkBox;}

    public void setUserCalendarId(UserCalendar userCalendarId) {this.userCalendarId = userCalendarId;}
}
