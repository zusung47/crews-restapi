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

    public UsercalendarDTO updateUserCalendarByUserId(User userId, UsercalendarDTO usercalendarDTO) {
        // userId를 사용하여 유저 캘린더를 조회합니다.
        UserCalendar userCalendar = userCalendarRepository.findForUpdateByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저 캘린더가 없습니다. userId: " + userId));

        // 유저 캘린더의 정보를 업데이트합니다.
        userCalendar.setStartDate(usercalendarDTO.getStartDate());
        userCalendar.setEndDate(usercalendarDTO.getEndDate());
        userCalendar.setTitle(usercalendarDTO.getTitle());
        userCalendar.setCalendarContent(usercalendarDTO.getCalendarContent());
        userCalendar.setDeleteStatus(usercalendarDTO.getDeleteStatus());
        userCalendar.setTime(usercalendarDTO.getTime());

        // 유저 캘린더를 저장하고 업데이트된 DTO를 반환합니다.
        UserCalendar updatedUserCalendar = userCalendarRepository.save(userCalendar);
        return modelMapper.map(updatedUserCalendar, UsercalendarDTO.class);
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