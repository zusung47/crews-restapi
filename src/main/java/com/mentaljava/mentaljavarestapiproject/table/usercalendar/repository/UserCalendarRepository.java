package com.mentaljava.mentaljavarestapiproject.table.usercalendar.repository;

import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.entity.UserCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserCalendarRepository extends JpaRepository<UserCalendar, Integer> {
    List<UserCalendar> findByUserId(User userId);

    List<UserCalendar> findForUpdateByUserId(User userId);

    List<UserCalendar> findByStartDate(Date startDate);
    List<UserCalendar> findByEndDate(Date endDate);

}
