package com.mentaljava.mentaljavarestapiproject.table.usercalendar.service;

import com.mentaljava.mentaljavarestapiproject.table.crew.entity.Crew;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.dto.UsercalendarDTO;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.entity.UserCalendar;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.repository.UserCalendarRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    public List<UsercalendarDTO> findUserCalendarsByUserId(User userId) {
        List<UserCalendar> userCalendarList = userCalendarRepository.findByUserId(userId);
        return userCalendarList.stream()
                .map(userCalendar -> modelMapper.map(userCalendar, UsercalendarDTO.class))
                .collect(Collectors.toList());
    }
    public List<UsercalendarDTO> updateUserCalendarByUserId(User userId, Integer userCalendarId, UsercalendarDTO usercalendarDTO) {
        // userId를 사용하여 유저 캘린더를 조회합니다.
        List<UserCalendar> userCalendars = userCalendarRepository.findForUpdateByUserId(userId);

        // 조회된 유저 캘린더들을 각각 업데이트합니다.
        List<UserCalendar> updatedUserCalendars = new ArrayList<>();
        for (UserCalendar userCalendar : userCalendars) {
            if (userCalendar.getUserCalendarId().equals(userCalendarId)) {
                // 해당하는 userCalendarId를 가진 이벤트에 대해서만 값을 업데이트합니다.
                userCalendar.setStartDate(usercalendarDTO.getStartDate());
                userCalendar.setEndDate(usercalendarDTO.getEndDate());
                userCalendar.setTitle(usercalendarDTO.getTitle());
                userCalendar.setCalendarContent(usercalendarDTO.getCalendarContent());
                userCalendar.setDeleteStatus(usercalendarDTO.getDeleteStatus());
                userCalendar.setTime(usercalendarDTO.getTime());

                // 업데이트된 유저 캘린더를 저장하고 리스트에 추가합니다.
                updatedUserCalendars.add(userCalendarRepository.save(userCalendar));
            }
        }

        // DTO 리스트로 변환하여 반환합니다.
        return updatedUserCalendars.stream()
                .map(userCalendar -> modelMapper.map(userCalendar, UsercalendarDTO.class))
                .collect(Collectors.toList());
    }



    public List<UsercalendarDTO> findUserCalendarsByStartDate(Date startDate) {
        List<UserCalendar> userCalendarList = userCalendarRepository.findByStartDate(startDate);
        return userCalendarList.stream()
                .map(userCalendar -> modelMapper.map(userCalendar, UsercalendarDTO.class))
                .collect(Collectors.toList());
    }

    public List<UsercalendarDTO> findUserCalendarsByEndDate(Date endDate) {
        List<UserCalendar> userCalendarList = userCalendarRepository.findByEndDate(endDate);
        return userCalendarList.stream()
                .map(userCalendar -> modelMapper.map(userCalendar, UsercalendarDTO.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public UsercalendarDTO insertUsercalendar(User userId, UsercalendarDTO usercalendarDTO) {
        UserCalendar newUserCalendar = new UserCalendar();

        // 새로운 유저 캘린더의 정보를 설정합니다.
        newUserCalendar.setUserId(userId);
        newUserCalendar.setStartDate(usercalendarDTO.getStartDate());
        newUserCalendar.setEndDate(usercalendarDTO.getEndDate());
        newUserCalendar.setTitle(usercalendarDTO.getTitle());
        newUserCalendar.setCalendarContent(usercalendarDTO.getCalendarContent());
        newUserCalendar.setDeleteStatus(usercalendarDTO.getDeleteStatus());
        newUserCalendar.setTime(usercalendarDTO.getTime());

        // 새로운 유저 캘린더를 저장하고 저장된 DTO를 반환합니다.
        UserCalendar savedUserCalendar = userCalendarRepository.save(newUserCalendar);
        return modelMapper.map(savedUserCalendar, UsercalendarDTO.class);
    }


}