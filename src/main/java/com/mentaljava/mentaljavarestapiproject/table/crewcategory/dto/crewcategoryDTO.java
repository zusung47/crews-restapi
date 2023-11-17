package com.mentaljava.mentaljavarestapiproject.table.crewcategory.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class crewcategoryDTO {

    private int categoryCode;

    private String categoryName;

}
