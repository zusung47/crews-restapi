package com.mentaljava.mentaljavarestapiproject.table.crewcheck.repository;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crewcheck.entity.CrewCheck;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CrewCheckRepository extends JpaRepository<CrewCheck, Integer> {
    List<CrewCheck> findByCrewAndUser(Crew crew, User user);

    Page<CrewCheck> findByCrewAndUser(Crew crew, User user, Pageable paging);

    CrewCheck findByCrewAndUserAndToday(Crew crew, User user, LocalDate today);
}
