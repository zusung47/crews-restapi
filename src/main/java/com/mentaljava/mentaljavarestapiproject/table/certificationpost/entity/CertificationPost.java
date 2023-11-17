package com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity;

import javax.persistence.*;
import java.util.Date;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;


@Entity
@Table(name = "CERTIFICATION_POST")
@AllArgsConstructor
@Getter
@ToString
public class CertificationPost {

    @Id
    @Column(name = "POST_ID")
    private int postId;

    @Column(name = "POST_TITLE")
    private String postTitle;

    @Column(name = "POST_CONTENT")
    private String postContent;

    @Column(name = "POST_DATE")
    private Date postDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "CREW_ID")
    private Crew crewId;
}
