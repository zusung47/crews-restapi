package com.mentaljava.mentaljavarestapiproject.table.singleCalendar.service;

import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.dto.CalendarRequest;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.dto.RepeatDTO;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.dto.SingleCalendarDTO;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.entitiy.SingleCalendar;
import com.mentaljava.mentaljavarestapiproject.table.singleCalendar.repository.SingleCalendarRepository;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.Calendar;
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
    public List<SingleCalendarDTO> insertSingleCalendar(String userId, CalendarRequest calendarRequest) {

        User user = userRepository.findByUserId(userId);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        SingleCalendarDTO singleCalendarDTO = calendarRequest.getSingleCalendarDTO();
        RepeatDTO repeatDTO = calendarRequest.getRepeatDTO();

        List<SingleCalendarDTO> generatedCalendars = generateCalendars(singleCalendarDTO, repeatDTO);

        // 생성된 SingleCalendar 데이터를 저장
        for (SingleCalendarDTO generatedCalendar : generatedCalendars) {
            generatedCalendar.setUserId(userDTO);
            SingleCalendar singleCalendar = modelMapper.map(generatedCalendar, SingleCalendar.class);

            singleCalendarRepository.save(singleCalendar);
        }



        return generatedCalendars;
    }

    private List<SingleCalendarDTO> generateCalendars(SingleCalendarDTO singleCalendarDTO, RepeatDTO repeatDTO) {
        List<SingleCalendarDTO> generatedCalendars = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(repeatDTO.getStartDate());



        return generatedCalendars;
    }
}
