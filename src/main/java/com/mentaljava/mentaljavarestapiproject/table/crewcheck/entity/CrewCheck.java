package com.mentaljava.mentaljavarestapiproject.table.crewcheck.entity;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import lombok.*;
import org.springframework.data.domain.PageRequest;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CREW_CHECK")
@AllArgsConstructor
@Getter
@ToString
public class CrewCheck {

    @Id
    @Column(name = "CHECK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CREW_ID")
    private Crew crew;

    @Column(name = "TODAY")
    private LocalDate today;

    @Column(name = "IS_CHECK")
    private String isCheck;

    public CrewCheck() {}

    public Integer getCheckId() {
        return checkId;
    }

    public User getUser() {
        return user;
    }

    public Crew getCrew() {
        return crew;
    }

    public LocalDate getToday() {
        return today;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
}
