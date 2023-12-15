package com.mentaljava.mentaljavarestapiproject.table.crewlist.entity;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crewlistid.entity.CrewListId;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CREW_LIST")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CrewList {

    @EmbeddedId
    private CrewListId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @MapsId("crewId")
    @JoinColumn(name = "CREW_ID")
    private Crew crew;

    @Column(name = "APPROVAL_STATUS")
    private Integer approvalStatus;

    @Column(name = "IS_CAPTAIN")
    private String isCaptain;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "SCORE_STATUS")
    private String scoreStatus;

    @Column(name = "CHECK_COUNT")
    private Integer checkCount;

}
