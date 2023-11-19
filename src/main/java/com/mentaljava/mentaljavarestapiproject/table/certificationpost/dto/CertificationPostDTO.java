package com.mentaljava.mentaljavarestapiproject.table.certificationpost.dto;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CertificationPostDTO {

    private int postId;

    private String postTitle;

    private String postContent;

    private Date postDate;

    private User userId;

    private Crew crewId;
}
