package com.mentaljava.mentaljavarestapiproject.table.crewlistid.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
@Embeddable
public class CrewListId implements Serializable {

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "CREW__ID")
    private Integer crewId;

    public CrewListId() {}

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }
}
