package com.mentaljava.mentaljavarestapiproject.table.usercalendar.repository;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.entity.UserCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCalendarRepository extends JpaRepository<UserCalendar, Integer> {
    List<UserCalendar> findByUserId(User userId);
}
