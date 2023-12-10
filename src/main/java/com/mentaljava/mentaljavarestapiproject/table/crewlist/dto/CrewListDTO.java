package com.mentaljava.mentaljavarestapiproject.table.crewlist.dto;

import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewlistid.dto.CrewListIdDTO;
import com.mentaljava.mentaljavarestapiproject.table.crewlistid.entity.CrewListId;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CrewListDTO {

    private CrewListIdDTO id;

    private UserDTO user;

    private CrewDTO crew;

    private Integer approvalStatus;

    private String isCaptain;

}
