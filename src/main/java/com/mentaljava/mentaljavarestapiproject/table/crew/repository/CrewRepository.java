package com.mentaljava.mentaljavarestapiproject.table.crew.repository;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Integer> {
    List<Crew> findByRecruitmentStatus(String recruitmentStatus);

    List<Crew> findByCrewCategoryCode_CategoryCode(int crewCategoryCode);

    Page<Crew> findByCrewCategoryCode_CategoryCode(int crewCategoryCode, Pageable paging);

    Crew findByCrewId(Integer crewId);

    List<Crew> findByCrewNameContaining(String search);

    List<Crew> findByCaptain(User captain);
}
