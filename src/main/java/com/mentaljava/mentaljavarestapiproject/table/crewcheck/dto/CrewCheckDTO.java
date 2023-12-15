package com.mentaljava.mentaljavarestapiproject.table.crewcheck.dto;

import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CrewCheckDTO {

    private Integer checkId;

    private UserDTO user;

    private CrewDTO crew;

    private LocalDate today;

    private String isCheck;
}
