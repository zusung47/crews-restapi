package com.mentaljava.mentaljavarestapiproject.table.crewlist.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CREW_LIST")
@AllArgsConstructor
@Getter
@ToString
public class CrewList {

    //복합키이이이이이이이이이이어캐해해해해해
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "CREW__ID")
    private Integer crewId;

    @Column(name = "APPROVAL_STATUS")
    private Integer approvalStatus;
}
