package com.mentaljava.mentaljavarestapiproject.table.crewlist.dto;

import com.mentaljava.mentaljavarestapiproject.table.crewlistid.dto.CrewListIdDTO;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CrewlistDTO {

    private CrewListIdDTO CrewListid;

    private Integer approvalStatus;
}
