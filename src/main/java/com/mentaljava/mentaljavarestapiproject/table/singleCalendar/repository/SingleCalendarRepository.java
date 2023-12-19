package com.mentaljava.mentaljavarestapiproject.table.singleCalendar.repository;

import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.dto.SingleCalendarDTO;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.entitiy.SingleCalendar;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleCalendarRepository extends JpaRepository<SingleCalendar,Integer> {

    List<SingleCalendar> findByUserId(User user);

    List<SingleCalendar> findByUserIdAndGroupId(User user, String groupId);


    SingleCalendar findBySingleCalendarId(Integer singleCalendarId);
}
