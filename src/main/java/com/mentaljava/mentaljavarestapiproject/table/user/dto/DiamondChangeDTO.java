package com.mentaljava.mentaljavarestapiproject.table.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DiamondChangeDTO {

    private String userId;

    private Integer diamond;

    private String scoreStatus;
}
