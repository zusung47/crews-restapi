package com.mentaljava.mentaljavarestapiproject.table.usercalendar.repository;

import com.mentaljava.mentaljavarestapiproject.table.usercalendar.entity.UserCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCalendarRepository extends JpaRepository<UserCalendar, Integer> {
}
