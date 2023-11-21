package com.mentaljava.mentaljavarestapiproject.table.crew.dto;

import com.mentaljava.mentaljavarestapiproject.table.crewcategory.entity.CrewCategory;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CrewDTO {

    private Integer crewId;

    private String crewName;

    private User captain;

    private String introduction;

    private CrewCategory crewCategoryCode;

    private Date startDate;

    private Date endDate;

    private String crewRecruitmentPost;

    private String crewRecruitmentContent;

    private String recruitmentStatus;

    private Date creationDate;
}
