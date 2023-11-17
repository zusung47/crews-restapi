package com.mentaljava.mentaljavarestapiproject.table.crewlist.entity;

import com.mentaljava.mentaljavarestapiproject.table.crewlistid.entity.CrewListId;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CREW_LIST")
@AllArgsConstructor
@Getter
@ToString
public class CrewList {

    @EmbeddedId
    private CrewListId CrewListid;

    @Column(name = "APPROVAL_STATUS")
    private Integer approvalStatus;

    public CrewList() {}

    public void setCrewListid(CrewListId crewListid) {
        CrewListid = crewListid;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
