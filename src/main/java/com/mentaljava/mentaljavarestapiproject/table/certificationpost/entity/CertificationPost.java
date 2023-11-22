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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "POST_TITLE")
    private String postTitle;

    @Column(name = "POST_CONTENT")
    private String postContent;

    @Column(name = "POST_DATE")
    private Date postDate;

    @ManyToOne
    @JoinColumn(name = "CREW_ID")
    private Crew crewId;

    public CertificationPost() {}

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public void setCrewId(Crew crewId) {
        this.crewId = crewId;
    }
}
