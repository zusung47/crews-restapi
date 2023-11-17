package com.mentaljava.mentaljavarestapiproject.table.crewlist.dto;

import com.mentaljava.mentaljavarestapiproject.table.crewlistid.entity.CrewListId;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class crewlistDTO {

    private CrewListId CrewListid;

    private Integer approvalStatus;
}
