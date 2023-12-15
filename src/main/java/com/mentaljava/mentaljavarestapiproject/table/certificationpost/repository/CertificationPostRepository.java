package com.mentaljava.mentaljavarestapiproject.table.certificationpost.repository;

import com.mentaljava.mentaljavarestapiproject.table.certificationpost.entity.CertificationPost;
import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertificationPostRepository extends JpaRepository<CertificationPost, Integer> {

    CertificationPost findByPostId(Integer postId);

//    @Query("SELECT c FROM CertificationPost c WHERE c.crewId=?1")
    List<CertificationPost> findByCrewId_CrewId(Integer crewId);

    Page<CertificationPost> findByCrewId_CrewId(Integer crewId, Pageable paging);

    Page<CertificationPost> findByCrewId(Crew crew, Pageable paging);
}
