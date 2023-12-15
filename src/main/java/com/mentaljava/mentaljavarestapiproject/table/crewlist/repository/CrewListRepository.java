package com.mentaljava.mentaljavarestapiproject.table.crewlist.repository;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.crewlist.entity.CrewList;
import com.mentaljava.mentaljavarestapiproject.table.crewlistid.entity.CrewListId;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CrewListRepository extends JpaRepository<CrewList, CrewListId> {
    List<CrewList> findAllById_CrewId(Integer crewId);

    CrewList findById_CrewIdAndId_UserId(Integer crewId, String userId);

    List<CrewList> findById_CrewIdAndApprovalStatus(Integer crewId, Integer approvalStatus);

    List<CrewList> findCrewByUser_UserId(String userId);

    List<CrewList> findByUserAndApprovalStatus(User user, int i);

    @Query("SELECT cl FROM CrewList cl WHERE cl.user = :user AND cl.approvalStatus = 1 AND cl.endDate >= CURRENT_DATE")
    Page<CrewList> findByUserAndApprovalStatus(@Param("user") User user, Pageable pageable);

    @Query("SELECT cl FROM CrewList cl WHERE cl.user = :user AND cl.approvalStatus = 1 AND cl.endDate <= CURRENT_DATE")
    Page<CrewList> findByUserAndEndCrew(User user, Pageable paging);

    @Query("SELECT cl FROM CrewList cl WHERE cl.user = :user AND cl.approvalStatus = 1 AND cl.endDate <= CURRENT_DATE")
    List<CrewList> findByUserAndApprovalStatusAndDate(User user);

    List<CrewList> findByCrew(Crew crew);

    List<CrewList> findByCrewAndApprovalStatusNot(Crew crew, Integer approvalStatus);

    Page<CrewList> findByCrew(Crew crew, Pageable paging);

    Page<CrewList> findByCrewAndApprovalStatusNot(Crew crew, Integer approvalStatus, Pageable paging);
}
