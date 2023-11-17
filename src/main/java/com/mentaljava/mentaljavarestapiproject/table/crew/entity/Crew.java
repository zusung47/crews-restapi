package com.mentaljava.mentaljavarestapiproject.table.crew.entity;

import com.mentaljava.mentaljavarestapiproject.table.crewcategory.entity.CrewCategory;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CREW")
@AllArgsConstructor
@Getter
@ToString
public class Crew {

    @Id
    @Column(name = "CREW_ID")
    private int crewId;

    @Column(name = "CREW_NAME")
    private String crewName;

    @ManyToOne
    @JoinColumn(name = "CAPTAIN")
    private User captain;

    @Column(name = "INTRODUCTION")
    private String introduction;

    @ManyToOne
    @JoinColumn(name = "CREW_CATEGORYCODE")
    private CrewCategory crewCategoryCode;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "CREW_RECRUITMENT_POST")
    private String crewRecruitmentPost;

    @Column(name = "CREW_RECRUITMENT_CONTENT")
    private String crewRecruitmentContent;

    @Column(name = "RECRUITMENT_STATUS")
    private String recruitmentStatus;

    @Column(name = "CREATION_DATE")
    private Date creationDate;
}
