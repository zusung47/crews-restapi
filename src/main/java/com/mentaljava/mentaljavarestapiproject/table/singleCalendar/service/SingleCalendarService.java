package com.mentaljava.mentaljavarestapiproject.table.singleCalendar.service;

import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.dto.CalendarRequest;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.dto.SingleCalendarDTO;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.entitiy.SingleCalendar;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.repository.SingleCalendarRepository;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.user.repository.UserRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SingleCalendarService {

    private final SingleCalendarRepository singleCalendarRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public List<SingleCalendarDTO> singleCalendarSelect(String userId) {

        User user = userRepository.findByUserId(userId);

        List<SingleCalendar> singleCalendars = singleCalendarRepository.findByUserId(user);
        List<SingleCalendarDTO> singleCalendarDTOS = singleCalendars.stream()
                .map(singleCalendar -> modelMapper.map(singleCalendar, SingleCalendarDTO.class))
                .collect(Collectors.toList());

        return singleCalendarDTOS;

    }

    @Transactional
    public CalendarRequest insertSingleCalendar(String userId, CalendarRequest calendarRequest) {
        return null;
    }
}
