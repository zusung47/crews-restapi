package com.mentaljava.mentaljavarestapiproject.table.crewlistid.dto;

import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CrewListIdDTO {

    private String userId;

    private Integer crewId;
}
