package com.mentaljava.mentaljavarestapiproject.table.crewcheck.repository;

import com.mentaljava.mentaljavarestapiproject.table.crewcheck.entity.CrewCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewCheckRepository extends JpaRepository<CrewCheck, Integer> {
}
