package com.mentaljava.mentaljavarestapiproject.table.crewlist.repository;

import com.mentaljava.mentaljavarestapiproject.table.crewlist.entity.CrewList;
import com.mentaljava.mentaljavarestapiproject.table.crewlistid.entity.CrewListId;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewListRepository extends JpaRepository<CrewList, CrewListId> {
    List<CrewList> findAllById_CrewId(Integer crewId);

    CrewList findById_CrewIdAndId_UserId(Integer crewId, String userId);

    List<CrewList> findById_CrewIdAndApprovalStatus(Integer crewId, Integer approvalStatus);

    List<CrewList> findCrewByUser_UserId(String userId);

    List<CrewList> findByUserAndApprovalStatus(User user, int i);

    Page<CrewList> findByUserAndApprovalStatus(User user, int i, Pageable paging);
}
