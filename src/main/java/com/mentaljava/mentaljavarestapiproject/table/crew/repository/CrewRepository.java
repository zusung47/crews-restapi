package com.mentaljava.mentaljavarestapiproject.table.crew.repository;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepository extends JpaRepository<Crew, Integer> {
}
