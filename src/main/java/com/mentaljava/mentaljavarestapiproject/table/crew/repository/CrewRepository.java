package com.mentaljava.mentaljavarestapiproject.table.crew.repository;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrewRepository extends JpaRepository<Crew, Integer> {
    List<Crew> findByRecruitmentStatus(String recruitmentStatus);

    List<Crew> findByCrewCategoryCode_CategoryCode(int crewCategoryCode);

    Crew findByCrewId(Integer crewId);
}
