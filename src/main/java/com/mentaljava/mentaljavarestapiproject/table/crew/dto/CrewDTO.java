package com.mentaljava.mentaljavarestapiproject.table.crew.dto;

import com.mentaljava.mentaljavarestapiproject.table.crewcategory.dto.CrewCategoryDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
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

    private UserDTO captain;

    private String introduction;

    private CrewCategoryDTO crewCategoryCode;

    private Date startDate;

    private Date endDate;

    private String crewRecruitmentPost;

    private String crewRecruitmentContent;

    private String recruitmentStatus;

    private Date creationDate;
}
