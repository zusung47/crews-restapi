package com.mentaljava.mentaljavarestapiproject.table.crew.entity;

import com.mentaljava.mentaljavarestapiproject.table.crewcategory.entity.CrewCategory;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "CREW")
@AllArgsConstructor
@Getter
@ToString
public class Crew {

    @Id
    @Column(name = "CREW_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer crewId;

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
    private LocalDate creationDate;

    public Crew() {}

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public void setCaptain(User captain) {
        this.captain = captain;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setCrewCategoryCode(CrewCategory crewCategoryCode) {
        this.crewCategoryCode = crewCategoryCode;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCrewRecruitmentPost(String crewRecruitmentPost) {
        this.crewRecruitmentPost = crewRecruitmentPost;
    }

    public void setCrewRecruitmentContent(String crewRecruitmentContent) {
        this.crewRecruitmentContent = crewRecruitmentContent;
    }

    public void setRecruitmentStatus(String recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
