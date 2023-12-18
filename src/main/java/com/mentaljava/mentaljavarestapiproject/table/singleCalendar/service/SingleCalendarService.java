package com.mentaljava.mentaljavarestapiproject.table.singleCalendar.service;

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
    public List<SingleCalendarDTO> insertSingleCalendar(String userId, SingleCalendarDTO singleCalendarDTO) {
        User user = userRepository.findByUserId(userId);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        List<SingleCalendarDTO> generatedCalendars = generateCalendars(singleCalendarDTO, userDTO);

        // 생성된 SingleCalendar 데이터를 저장
        List<SingleCalendarDTO> savedCalendars = new ArrayList<>();
        for (SingleCalendarDTO generatedCalendar : generatedCalendars) {
            SingleCalendar singleCalendar = modelMapper.map(generatedCalendar, SingleCalendar.class);
            singleCalendar.setUserId(user);
            singleCalendarRepository.save(singleCalendar);
            savedCalendars.add(modelMapper.map(singleCalendar, SingleCalendarDTO.class));
        }

        return savedCalendars;
    }

    private List<SingleCalendarDTO> generateCalendars(SingleCalendarDTO singleCalendarDTO, UserDTO userDTO) {
        List<SingleCalendarDTO> generatedCalendars = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(singleCalendarDTO.getFirstDate());

        for (int i = 0; i < singleCalendarDTO.getRepeatNum(); i++) {
            SingleCalendarDTO generatedCalendar = new SingleCalendarDTO();
            generatedCalendar.setStartDate(calendar.getTime());
            generatedCalendar.setUserId(userDTO);
            generatedCalendar.setGroupId(singleCalendarDTO.getGroupId());
            generatedCalendar.setTitle(singleCalendarDTO.getTitle());

            // 기타 필요한 데이터 설정

            generatedCalendars.add(generatedCalendar);

            // 다음 반복일 계산
            calendar.add(Calendar.DAY_OF_MONTH, singleCalendarDTO.getRepeatNum());
        }

        return generatedCalendars;
    }
}
