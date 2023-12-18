package com.mentaljava.mentaljavarestapiproject.table.singleCalendar.entitiy;


import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "SINGLECALENDAR")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SingleCalendar {

    @Id
    @Column(name = "SINGLE_CALENDAR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer singleCalendarId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "GROUP_ID")
    private Integer groupId;

    @Column(name = "TITLE")
    private String title;


}
