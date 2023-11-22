package com.mentaljava.mentaljavarestapiproject.table.usercalendar.service;

import com.mentaljava.mentaljavarestapiproject.table.usercalendar.dto.UsercalendarDTO;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.entity.UserCalendar;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.repository.UserCalendarRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserCalendarService {

    private final UserCalendarRepository userCalendarRepository;
    private final ModelMapper modelMapper;

    public UserCalendarService(UserCalendarRepository userCalendarRepository, ModelMapper modelMapper) {
        this.userCalendarRepository = userCalendarRepository;
        this.modelMapper = modelMapper;
    }

    public List<UsercalendarDTO> findAllUserCalendarList() {
        List<UserCalendar> userCalendarList = userCalendarRepository.findAll();
        List<UsercalendarDTO> usercalendarDTOList = userCalendarList.stream().map(userCalendar -> modelMapper.map(userCalendar, UsercalendarDTO.class)).collect(Collectors.toList());
        return usercalendarDTOList;
    }
}