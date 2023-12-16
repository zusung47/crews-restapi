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
        List<UserCalendar> userCalendars = userCalendarRepository.findForUpdateByUserId(userId);

        List<UserCalendar> updatedUserCalendars = new ArrayList<>();
        for (UserCalendar userCalendar : userCalendars) {
            if (userCalendar.getUserCalendarId().equals(userCalendarId)) {

                userCalendar.setStartDate(usercalendarDTO.getStartDate());
                userCalendar.setEndDate(usercalendarDTO.getEndDate());
                userCalendar.setTitle(usercalendarDTO.getTitle());
                userCalendar.setCalendarContent(usercalendarDTO.getCalendarContent());
                userCalendar.setDeleteStatus(usercalendarDTO.getDeleteStatus());
                userCalendar.setTime(usercalendarDTO.getTime());
                userCalendar.setColor(usercalendarDTO.getColor());

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
    public String deleteUserCalendar(User userId, Integer userCalendarId) {
        // userId를 사용하여 유저 캘린더를 조회합니다.
        List<UserCalendar> userCalendars = userCalendarRepository.findForUpdateByUserId(userId);

        // 삭제할 유저 캘린더를 찾아서 삭제합니다.
        for (UserCalendar userCalendar : userCalendars) {
            if (userCalendar.getUserCalendarId().equals(userCalendarId)) {
                userCalendarRepository.delete(userCalendar);
                return "삭제 성공했습니다!"; // 삭제 성공
            }
        }
        return "삭제에 실패했습니다!"; // 해당하는 userCalendarId를 가진 이벤트를 찾을 수 없음
    }


}