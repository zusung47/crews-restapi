package com.mentaljava.mentaljavarestapiproject.table.crew.repository;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CrewRepository extends JpaRepository<Crew, Integer> {
    List<Crew> findByRecruitmentStatus(String recruitmentStatus);

    List<Crew> findByCrewCategoryCode_CategoryCode(int crewCategoryCode);

    Page<Crew> findByCrewCategoryCode_CategoryCode(int crewCategoryCode, Pageable paging);

    Crew findByCrewId(Integer crewId);

    List<Crew> findByCrewNameContaining(String search);

    Page<Crew> findByCrewNameContaining(String search, Pageable paging);




    @Query("SELECT c FROM Crew c WHERE c.captain = :captain AND c.endDate >= CURRENT_DATE")
    Page<Crew> findByCaptain(@Param("captain") User captain, Pageable pageable);


    List<Crew> findByCaptain(User user);
}
